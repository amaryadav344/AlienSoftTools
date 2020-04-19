package com.webstudio.hub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@DependsOn("AppConfig")
public class DatabaseConfig {
    @Autowired
    private AppConfig appConfig;

    @Bean(name = "HubDataSource")
    public DataSource HubDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(appConfig.getHubConfig().getDatabaseConnection().getDatabaseDriver())
                .url(appConfig.getHubConfig().getDatabaseConnection().getDatabaseUrl())
                .username(appConfig.getHubConfig().getDatabaseConnection().getDatabaseUsername())
                .password(appConfig.getHubConfig().getDatabaseConnection().getDatabasePassword())
                .build();
    }

    @Bean(name = "HubDB")
    public JdbcTemplate HubDB(@Qualifier("HubDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "BusinessDataSource")
    public DataSource BusinessDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseDriver())
                .url(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUrl())
                .username(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUsername())
                .password(appConfig.getBusinessConfig().getDatabaseConnection().getDatabasePassword())
                .build();
    }

    @Bean(name = "BusinessDB")
    public JdbcTemplate BusinessDB(@Qualifier("BusinessDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
