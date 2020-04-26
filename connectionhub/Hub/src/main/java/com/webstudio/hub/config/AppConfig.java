package com.webstudio.hub.config;

import com.business.utils.FileHelper;
import com.business.utils.XMLWorker;
import com.business.utils.config.BusinessConfig;
import com.business.utils.HelperFunctions;
import com.webstudio.hub.models.Branch;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component("AppConfig")
public class AppConfig {
    private BusinessConfig businessConfig;
    private HubConfig hubConfig;

    public BusinessConfig getBusinessConfig() {
        return businessConfig;
    }

    public void setBusinessConfig(BusinessConfig businessConfig) {
        this.businessConfig = businessConfig;
    }

    public HubConfig getHubConfig() {
        return hubConfig;
    }

    public void setHubConfig(HubConfig hubConfig) {
        this.hubConfig = hubConfig;
    }

    @PostConstruct
    public void InitializeConfig() throws IOException {
        String content = FileHelper.ReadCompleteFile(HelperFunctions.getExecutableHomePath(AppConfig.class, "HubConfig.xml"));
        HubConfig hub = XMLWorker.getInstance().readCustomType(content, HubConfig.class);
        String content2 = FileHelper.ReadCompleteFile(hub.getBranches().stream().filter(Branch::isDefault).findFirst().get().getBusinessConfigPath());
        BusinessConfig businessConfig = XMLWorker.getInstance().readCustomType(content2, BusinessConfig.class);
        setBusinessConfig(businessConfig);
        setHubConfig(hub);

    }
}
