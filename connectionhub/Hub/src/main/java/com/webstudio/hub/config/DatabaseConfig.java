package com.webstudio.hub.config;

import com.webstudio.hub.models.HubConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean(name = "HubDataSource")
    public DataSource HubDataSource() {
        HubConfig hubConfig = HubConfig.getInstance();
        return DataSourceBuilder.create()
                .driverClassName(hubConfig.getDatabaseConnection().getDatabaseDriver())
                .url(hubConfig.getDatabaseConnection().getDatabaseUrl())
                .username(hubConfig.getDatabaseConnection().getDatabaseUsername())
                .username(hubConfig.getDatabaseConnection().getDatabasePassword())
                .build();
    }

    @Bean(name = "HubDB")
    public JdbcTemplate HubDB(@Qualifier("HubDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "BusinessDataSource")
    public DataSource BusinessDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url("jdbc:sqlserver://DESKTOP-0IB7S30;databaseName=DEMO")
                .username("DEVUSER")
                .password("DEVPS")
                .build();
    }

    @Bean(name = "BusinessDB")
    public JdbcTemplate BusinessDB(@Qualifier("BusinessDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }


}
