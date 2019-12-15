package com.webstudio.connectionhub.models.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IBusinessObject {
    @JacksonXmlElementWrapper(localName = "customMethods")
    @JacksonXmlProperty(localName = "customMethod")
    ICustomMethod[] customMethods;
    @JacksonXmlElementWrapper(localName = "objectMethods")
    @JacksonXmlProperty(localName = "objectMethod")
    IObjectMethod[] objectMethods;

    public IBusinessObject() {
    }

    public ICustomMethod[] getCustomMethods() {
        return customMethods;
    }

    public void setCustomMethods(ICustomMethod[] customMethods) {
        this.customMethods = customMethods;
    }

    public IObjectMethod[] getObjectMethods() {
        return objectMethods;
    }

    public void setObjectMethods(IObjectMethod[] objectMethods) {
        this.objectMethods = objectMethods;
    }
}
