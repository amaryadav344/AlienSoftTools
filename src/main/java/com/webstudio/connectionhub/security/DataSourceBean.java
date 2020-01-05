package com.webstudio.connectionhub.security;

import com.business.utils.AppConfigRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Component
public class DataSourceBean {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getDataSource() throws IOException {
        AppConfigRepository appConfigRepository = AppConfigRepository.getInstance();
        return DataSourceBuilder
                .create()
                .url(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_URL))
                .username(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_USERNAME))
                .password(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_PASSWORD))
                .driverClassName(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_DRIVER))
                .build();
    }
}