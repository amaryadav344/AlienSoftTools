package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
class IButton extends IView {
    String type;
    @JacksonXmlProperty(isAttribute = true)
    String text;
    @JacksonXmlProperty(isAttribute = true)
    String onClick;
    @JacksonXmlProperty(isAttribute = true)
    String method;
    @JacksonXmlProperty(isAttribute = true)
    String form;
    NavigationParameter[] navigationParameters;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public NavigationParameter[] getNavigationParameters() {
        return navigationParameters;
    }

    public void setNavigationParameters(NavigationParameter[] navigationParameters) {
        this.navigationParameters = navigationParameters;
    }

    public IButton() {
    }


    public String getType() {
        return type;
    }

    @JsonIgnore()
    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
