package com.webstudio.hub.config;

import com.business.utils.FileHelper;
import com.business.utils.HelperFunctions;
import com.business.utils.XMLWorker;
import com.business.utils.config.BusinessConfig;
import com.webstudio.hub.common.Constants;
import com.webstudio.hub.models.Branch;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component(Constants.ApplicationBeans.APP_CONFIG)
public class AppConfig {
    private BusinessConfig businessConfig;
    private HubConfig hubConfig;

    public BusinessConfig getBusinessConfig() {
        return businessConfig;
    }

    private void setBusinessConfig(BusinessConfig businessConfig) {
        this.businessConfig = businessConfig;
    }

    HubConfig getHubConfig() {
        return hubConfig;
    }

    private void setHubConfig(HubConfig hubConfig) {
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
