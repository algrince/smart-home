package com.algrince.smarthome.configs;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;

@TestConfiguration
public class TestDBConfig {
    @Container
    static MySQLContainer<?> container;

    @Bean
    @Primary
    public DataSource getDataSource() {
        container = new MySQLContainer<>("mysql:latest")
                .withDatabaseName("smart-home")
                .withUsername("root")
                .withPassword("root");

//        initialize database

        container.start();

        var dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://%s:%s/smart-home"
                .formatted(container.getHost(), container.getMappedPort(3306)));
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");

        return dataSourceBuilder.build();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(getDataSource());
        liquibase.setChangeLog("classpath:db/changelogs/main-changelog.yaml");
        return liquibase;
    }

}
