package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ISection extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    String title;
    IGrid grid;
    String type;

    public ISection(String ID, String title, IGrid grid) {
        this.id = ID;
        this.title = title;
        this.grid = grid;
    }

    public ISection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IGrid getGrid() {
        return grid;
    }

    public void setGrid(IGrid grid) {
        this.grid = grid;
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
