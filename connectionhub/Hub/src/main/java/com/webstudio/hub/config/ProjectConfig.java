package com.webstudio.hub.config;

import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProjectConfig {
    @Autowired
    AppConfig appConfig;

    @Bean("DefaultBranch")
    @DependsOn("AppConfig")
    public Branch GetDefaultBranch() {
        return appConfig.getHubConfig().getBranches().stream().filter(Branch::isDefault).findFirst().get();
    }

}
