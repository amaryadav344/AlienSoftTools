package com.webstudio.hub.security;

import com.webstudio.hub.models.HubConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@Configuration
@Component
public class DataSourceBean {
    Logger logger = Logger.getLogger(getClass().getName());

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getDataSource() throws IOException, URISyntaxException {
        HubConfig hubConfig = HubConfig.getInstance();
        logger.info(hubConfig.getDatabaseConnection().getDatabaseUsername());
        return DataSourceBuilder
                .create()
                .url(hubConfig.getDatabaseConnection().getDatabaseUrl())
                .username(hubConfig.getDatabaseConnection().getDatabaseUsername())
                .password(hubConfig.getDatabaseConnection().getDatabasePassword())
                .driverClassName(hubConfig.getDatabaseConnection().getDatabaseDriver())
                .build();
    }
}