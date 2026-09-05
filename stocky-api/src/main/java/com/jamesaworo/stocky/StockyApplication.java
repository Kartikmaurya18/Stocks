package com.jamesaworo.stocky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class StockyApplication {

    public static void main(String[] args) {
        discardUnexpandedShellPlaceholderProperties();
        SpringApplication.run(StockyApplication.class, args);
    }

    /**
     * Our Railway deployment's launch command is (still, mysteriously) the
     * project's old Procfile command - "java -Dserver.port=$PORT
     * -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE ... -jar
     * stocky-api/target/*.jar" - executed directly with no shell in
     * between. A shell would normally substitute "$PORT" etc. with the
     * real environment variable value before Java ever sees it; without
     * one, Java receives the literal, unexpanded text (dollar sign and
     * all) as each system property's value, e.g. server.port="$PORT",
     * which then fails Spring's property binding (NumberFormatException
     * trying to parse "$PORT" as an Integer) or otherwise poisons the
     * property with garbage.
     * <p>
     * Discard any system property whose value still looks like an
     * un-expanded "$VAR" placeholder, so Spring falls through to its
     * normal handling instead: the real OS environment variables (which
     * Railway does set correctly - just not into these particular system
     * properties), and application-prod.properties' own correctly-written
     * "server.port=${PORT}" placeholder.
     */
    private static void discardUnexpandedShellPlaceholderProperties() {
        List<String> poisonedKeys = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String && ((String) value).startsWith("$")) {
                poisonedKeys.add(String.valueOf(entry.getKey()));
            }
        }
        // Clear in a second pass - System.clearProperty() mutates the same
        // Properties map System.getProperties() returned above, so removing
        // entries while iterating over it directly would throw
        // ConcurrentModificationException.
        for (String key : poisonedKeys) {
            System.clearProperty(key);
        }
    }

}
