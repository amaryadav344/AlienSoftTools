package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ICustomMethod {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String mode;
    @JacksonXmlProperty(isAttribute = true)
    boolean loadPrimaryObject;
    @JacksonXmlElementWrapper(localName = "loadMappings")
    @JacksonXmlProperty(localName = "loadMapping")
    ILoadMapping[] loadMapping;

    public ICustomMethod() {
    }

    public ICustomMethod(String name, String mode, boolean loadPrimaryObject, ILoadMapping[] loadMapping) {
        this.name = name;
        this.mode = mode;
        this.loadPrimaryObject = loadPrimaryObject;
        this.loadMapping = loadMapping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isLoadPrimaryObject() {
        return loadPrimaryObject;
    }

    public void setLoadPrimaryObject(boolean loadPrimaryObject) {
        this.loadPrimaryObject = loadPrimaryObject;
    }

    public ILoadMapping[] getLoadMapping() {
        return loadMapping;
    }

    public void setLoadMapping(ILoadMapping[] loadMapping) {
        this.loadMapping = loadMapping;
    }
}
