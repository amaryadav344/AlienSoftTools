package com.business.utils.models.UI;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IParameter {
    @JacksonXmlProperty(isAttribute = true)
    String dataType;
    @JacksonXmlProperty(isAttribute = true)
    String name;
}
