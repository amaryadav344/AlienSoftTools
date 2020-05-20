package com.business.utils.models.UI;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class ILoadDetails {
    @JacksonXmlProperty(isAttribute = true)
    String serviceName;
    @JacksonXmlElementWrapper(localName = "Parameters")
    @JacksonXmlProperty(localName = "Parameter")
    List<IParameter> parameters;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<IParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<IParameter> parameters) {
        this.parameters = parameters;
    }
}