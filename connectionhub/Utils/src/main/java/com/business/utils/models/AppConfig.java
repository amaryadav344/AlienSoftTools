package com.business.utils.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "appconfig")
public class AppConfig {
    @JacksonXmlProperty(localName = "config")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Config> Configs = new ArrayList<>();

    public AppConfig() {
    }

    public List<Config> getConfigs() {
        return Configs;
    }

    public void setConfigs(List<Config> configs) {
        Configs = configs;
    }
}
