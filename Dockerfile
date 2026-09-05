# --- Build stage ---
# Uses JDK 11 to match stocky-api's <source>/<target> and Lombok 1.18.24,
# which fails to compile under newer JDKs (see maven-compiler-plugin config
# in stocky-api/pom.xml). The frontend-maven-plugin in stocky-web/pom.xml
# downloads its own pinned Node (v16.19.1), so no separate Node stage is
# needed here.
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app

# Copy the whole multi-module reactor (root pom + both modules) so Maven can
# resolve the stocky-web -> stocky-api module order and the resource-copy
# step that embeds the Angular build into the jar's static resources.
COPY pom.xml .
COPY stocky-web stocky-web
COPY stocky-api stocky-api

RUN mvn -q -B clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:11-jre-jammy
WORKDIR /app

COPY --from=build /app/stocky-api/target/stocky-api.jar app.jar

# Railway's platform has been launching this container with a hardcoded
# legacy command - "java -jar stocky-api/target/*.jar", left over from the
# old Procfile/Nixpacks-era config - instead of this image's own ENTRYPOINT,
# and neither the dashboard start-command field nor an explicit
# railway.json deploy.startCommand override has stopped it. The error
# message ("...target/*.jar", asterisk un-expanded) shows that string is
# passed to java literally, with no shell globbing involved. Linux allows a
# literal '*' in a filename, so create a file with that exact literal name
# - it satisfies the broken command whether or not a shell ever expands it,
# since a real shell glob "*.jar" also matches a file literally named
# "*.jar".
RUN mkdir -p stocky-api/target && cp app.jar 'stocky-api/target/*.jar'
# Also hedge against Railway's launcher not honoring this image's WORKDIR
# (i.e. running the command from / instead of /app) by placing the same
# literally-named file at the absolute root-level path too.
RUN mkdir -p /stocky-api/target && cp /app/app.jar '/stocky-api/target/*.jar'

RUN useradd -m stocky && chown -R stocky:stocky /app
USER stocky

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
