package com.webstudio.connectionhub.services;

import com.webstudio.connectionhub.repositories.SystemSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemSettingsService {
    @Autowired
    private SystemSettingsRepository systemSettingsRepository;
    private String XMlBasePath = null;
    private String BUSINESS_MODEL_LOCATION = null;
    private String PACKAGE_NAME = null;
    private String BIN_PATH = null;

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

    public String getPackageName() {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = systemSettingsRepository.findFirstBySettingName("PACKAGE_NAME").get(0).getSettingValue();
        }
        return PACKAGE_NAME;
    }

    public String getBinPath() {
        if (BIN_PATH == null) {
            BIN_PATH = systemSettingsRepository.findFirstBySettingName("BIN_PATH").get(0).getSettingValue();
        }
        return BIN_PATH;
    }
}
