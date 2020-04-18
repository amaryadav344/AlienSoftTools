package com.webstudio.hub.config;

import com.webstudio.hub.models.Branch;
import com.webstudio.hub.models.HubConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProjectConfig {
    @Bean("DefaultBranch")
    @DependsOn("HubConfig")
    public Branch GetDefaultBranch() {
        return HubConfig.getInstance().getBranches().stream().filter(Branch::isDefault).findFirst().get();
    }

}
