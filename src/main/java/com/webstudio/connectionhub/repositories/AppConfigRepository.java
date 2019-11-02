package com.webstudio.connectionhub.repositories;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.webstudio.connectionhub.models.AppConfig;

import java.io.File;
import java.io.IOException;

public class AppConfigRepository {
    private static AppConfigRepository mAppConfigRepository;
    AppConfig appConfig;

    private AppConfigRepository() throws IOException {
        appConfig = new XmlMapper().readValue(new File("C:\\Users\\Amardeep Yadav\\IdeaProjects\\bin\\AppConfig.xml"), AppConfig.class);
    }

    public static AppConfigRepository getInstance() throws IOException {
        if (mAppConfigRepository == null)
            mAppConfigRepository = new AppConfigRepository();
        return mAppConfigRepository;
    }

    public String getAppConfig(String ConfigName) {
        return appConfig.getConfigs().stream().filter(config -> config.getName().equals(ConfigName)).findFirst().get().getValue();
    }
}
