package com.webstudio.connectionhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemSettingsService {
    @Autowired
    private SystemSettingsRepository systemSettingsRepository;
    String XMlBasePath = null;

    public String getXmlBasePath() {
        return XMlBasePath == null ? systemSettingsRepository.findFirstBySettingName("XMLLOCATION").get(0).getSettingValue() : XMlBasePath;
    }
}
