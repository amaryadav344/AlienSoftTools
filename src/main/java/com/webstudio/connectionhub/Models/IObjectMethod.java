package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IObjectMethod {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String returnType;
    @JacksonXmlElementWrapper(localName = "objectParameters")
    @JacksonXmlProperty(localName = "objectParameter")
    IObjectParameter[] objectParameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public IObjectParameter[] getObjectParameters() {
        return objectParameters;
    }

    public void setObjectParameters(IObjectParameter[] objectParameters) {
        this.objectParameters = objectParameters;
    }

    public IObjectMethod(String name, String returnType, IObjectParameter[] objectParameters) {
        this.name = name;
        this.returnType = returnType;
        this.objectParameters = objectParameters;
    }

    public IObjectMethod() {

    }
}
