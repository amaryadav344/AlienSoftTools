package com.webstudio.hub.config;

import com.webstudio.hub.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@DependsOn(Constants.ApplicationBeans.APP_CONFIG)
public class DatabaseConfig {
    private AppConfig appConfig;

    @Bean(name = Constants.ApplicationBeans.HUB_DATASOURCE)
    public DataSource HubDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(appConfig.getHubConfig().getDatabaseConnection().getDatabaseDriver())
                .url(appConfig.getHubConfig().getDatabaseConnection().getDatabaseUrl())
                .username(appConfig.getHubConfig().getDatabaseConnection().getDatabaseUsername())
                .password(appConfig.getHubConfig().getDatabaseConnection().getDatabasePassword())
                .build();
    }

    @Bean(name = Constants.ApplicationBeans.HUB_DB)
    public JdbcTemplate HubDB(@Qualifier(Constants.ApplicationBeans.HUB_DATASOURCE) DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = Constants.ApplicationBeans.BUSINESS_DATASOURCE)
    public DataSource BusinessDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseDriver())
                .url(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUrl())
                .username(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUsername())
                .password(appConfig.getBusinessConfig().getDatabaseConnection().getDatabasePassword())
                .build();
    }

    @Bean(name = Constants.ApplicationBeans.BUSINESS_DB)
    public JdbcTemplate BusinessDB(@Qualifier(Constants.ApplicationBeans.BUSINESS_DATASOURCE) DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Autowired
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
