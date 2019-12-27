package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.webstudio.connectionhub.models.IXMLBase;


@JacksonXmlRootElement(localName = "form")
public class IForm extends IXMLBase {
    String type;
    IView[] views;

    public IForm() {
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IView[] getViews() {
        return views;
    }

    public void setViews(IView[] views) {
        this.views = views;
    }
}