package com.webstudio.hub.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Branch {
    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    String Name;
    @JacksonXmlProperty(localName = "Default", isAttribute = true)
    boolean Default;
    @JacksonXmlProperty(localName = "XMLPath")
    String XMLPath;
    @JacksonXmlProperty(localName = "PackageName")
    String PackageName;
    @JacksonXmlProperty(localName = "SourcePath")
    String SourcePath;
    @JacksonXmlElementWrapper(localName = "BusinessObjects")
    @JacksonXmlProperty(localName = "BusinessObject")
    List<String> BusinessObject;

    public Branch() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public String getXMLPath() {
        return XMLPath;
    }

    public void setXMLPath(String XMLPath) {
        this.XMLPath = XMLPath;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getSourcePath() {
        return SourcePath;
    }

    public void setSourcePath(String sourcePath) {
        SourcePath = sourcePath;
    }

    public List<String> getBusinessObject() {
        return BusinessObject;
    }

    public void setBusinessObject(List<String> businessObject) {
        BusinessObject = businessObject;
    }
}
