package com.webstudio.connectionhub.models.UI;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IGrid extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    String type;
    IGRow[] rows;


    public IGrid() {
    }

    public String getId() {
        return id;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IGRow[] getRows() {
        return rows;
    }

    public void setRows(IGRow[] rows) {
        this.rows = rows;
    }
}
