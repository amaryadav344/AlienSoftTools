package com.webstudio.connectionhub.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ILoadMapping {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String loadType;
    @JacksonXmlElementWrapper(localName = "loadParameters")
    @JacksonXmlProperty(localName = "loadParameter")
    ILoadParameter[] loadParameters;

    public ILoadMapping() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public ILoadParameter[] getLoadParameters() {
        return loadParameters;
    }

    public void setLoadParameters(ILoadParameter[] loadParameters) {
        this.loadParameters = loadParameters;
    }

    public ILoadMapping(String name, String loadType, ILoadParameter[] loadParameters) {
        this.name = name;
        this.loadType = loadType;
        this.loadParameters = loadParameters;
    }
}
