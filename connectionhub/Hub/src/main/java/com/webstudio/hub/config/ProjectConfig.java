package com.webstudio.hub.config;

import com.webstudio.hub.common.Constants;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProjectConfig {
    private AppConfig appConfig;

    @Bean(Constants.ApplicationBeans.DEFAULT_BRANCH)
    @DependsOn(Constants.ApplicationBeans.APP_CONFIG)
    public Branch GetDefaultBranch() {
        return appConfig.getHubConfig().getBranches().stream().filter(Branch::isDefault).findFirst().get();
    }

    @Autowired
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
