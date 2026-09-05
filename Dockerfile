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

RUN useradd -m stocky
USER stocky

COPY --from=build /app/stocky-api/target/stocky-api.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
