package com.business.utils;

import com.business.utils.models.AppConfig;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class AppConfigRepository {
    public static String DATABASE_URL = "DatabaseUrl";
    public static String DATABASE_USERNAME = "DatabaseUsername";
    public static String DATABASE_PASSWORD = "DatabasePassword";
    public static String DATABASE_DRIVER = "DatabaseDriver";
    public static String BASE_DIRECTORY = "BaseDirectory";
    public static String BIN_RELATIVE_PATH = "BinRelativePath";
    public static String XML_RELATIVE_PATH = "XMLRelativePath";
    public static String BUSINESS_MODELS_RELATIVE_PATH = "BusinessModelsRelativePath";
    public static String PACKAGE_NAME = "PackageName";
    private static AppConfigRepository mAppConfigRepository;
    AppConfig appConfig;

    private AppConfigRepository() throws IOException {
        appConfig = new XmlMapper().readValue(new File("C:\\Users\\Amardeep Yadav\\WebstormProjects\\Sourcecontrolroot\\AlienSoftDev\\bin\\AppConfig.xml"), AppConfig.class);
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
