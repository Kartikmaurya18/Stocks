package com.jamesaworo.stocky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NOTE: This codebase is mid-migration from a legacy "flat" package layout
 * (controller/service/serviceImpl/dao/entity/config.security) to a
 * clean-architecture "features/*" layout. Several modules (auth, product,
 * report, sale, stock, settings) exist in BOTH layouts with identical
 * @RequestMapping paths, identically-named @Component beans, and
 * identically-named JPA @Entity classes, which crashes Spring Boot at
 * startup (ambiguous mappings / conflicting bean definitions / duplicate
 * Hibernate entity mappings).
 * <p>
 * The features/* implementations are kept authoritative. The legacy
 * duplicates below are excluded from component scanning, entity scanning,
 * and JPA repository scanning so the app can boot. The "inventory" module
 * has no features/* equivalent, so its legacy controller/service/dao/entity
 * stack is left fully active. Nothing is deleted - these packages still
 * compile and can be removed later once the migration is confirmed complete.
 */
@SpringBootApplication
@ComponentScan(
        basePackages = "com.jamesaworo.stocky",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.controller\\.auth\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.controller\\.product\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.controller\\.report\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.controller\\.sale\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.controller\\.stock\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.config\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.serviceImpl\\.auth\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.serviceImpl\\.product\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.serviceImpl\\.report\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.serviceImpl\\.sale\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.serviceImpl\\.stock\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.jamesaworo\\.stocky\\.dao\\.sale\\.specification\\..*"),
        }
)
// Only scan features/* entities plus the legacy "inventory" module, which has
// no features/* equivalent. This avoids duplicate-Hibernate-entity-name
// crashes from the legacy entity.{auth,company,product,sale,settings,stock}
// packages, which are fully superseded by features/*.
@EntityScan(basePackages = {
        "com.jamesaworo.stocky.features",
        "com.jamesaworo.stocky.entity.inventory"
})
@EnableJpaRepositories(basePackages = {
        "com.jamesaworo.stocky.features",
        "com.jamesaworo.stocky.dao.inventory"
})
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
