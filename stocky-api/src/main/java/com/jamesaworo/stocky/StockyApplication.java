package com.jamesaworo.stocky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
        SpringApplication.run(StockyApplication.class, args);
    }

}
