package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IGColumn {
    @JacksonXmlProperty(isAttribute = true)
    int span;
    @JacksonXmlProperty(localName = "View")
    @JacksonXmlElementWrapper(useWrapping = false)
    IView[] controls;


    public IGColumn() {
    }

    IGColumn(int span, IView[] controls) {
        super();
        this.span = span;
        this.controls = controls;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public IView[] getControls() {
        return controls;
    }

    public void setControls(IView[] controls) {
        this.controls = controls;
    }
}
