package com.webstudio.connectionhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemSettingsService {
    @Autowired
    private SystemSettingsRepository systemSettingsRepository;
    String XMlBasePath = null;
    String BUSINESS_MODEL_LOCATION = null;

    public String getXmlBasePath() {
        if (XMlBasePath == null) {
            XMlBasePath = systemSettingsRepository.findFirstBySettingName("XMLLOCATION").get(0).getSettingValue();
        }
        return XMlBasePath;
    }

    public String getBusinessModelPath() {
        if (BUSINESS_MODEL_LOCATION == null) {
            BUSINESS_MODEL_LOCATION = systemSettingsRepository.findFirstBySettingName("BUSINESS_MODELS_LOCATION").get(0).getSettingValue();
        }
        return BUSINESS_MODEL_LOCATION;
    }
}
