package com.webstudio.hub.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Branch {
    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String Name;
    @JacksonXmlProperty(localName = "Default", isAttribute = true)
    private boolean Default;
    @JacksonXmlProperty(localName = "XMLPath")
    private String XMLPath;
    @JacksonXmlProperty(localName = "PackageName")
    private String PackageName;
    @JacksonXmlProperty(localName = "SourcePath")
    private String SourcePath;
    @JacksonXmlProperty(localName = "BusinessConfigPath")
    private String BusinessConfigPath;
    @JacksonXmlProperty(localName = "BusinessObjectBase")
    private String BusinessObjectBase;
    @JacksonXmlProperty(localName = "ServiceBase")
    private String ServiceBase;
    @JacksonXmlProperty(localName = "RepositoryBase")
    private String RepositoryBase;
    @JacksonXmlProperty(localName = "ValidationBase")
    private String ValidationBase;


    public String getBusinessObjectBase() {
        return BusinessObjectBase;
    }

    public void setBusinessObjectBase(String businessObjectBase) {
        BusinessObjectBase = businessObjectBase;
    }


    public String getServiceBase() {
        return ServiceBase;
    }

    public void setServiceBase(String serviceBase) {
        ServiceBase = serviceBase;
    }

    public String getRepositoryBase() {
        return RepositoryBase;
    }

    public void setRepositoryBase(String repositoryBase) {
        RepositoryBase = repositoryBase;
    }

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

    public String getBusinessConfigPath() {
        return BusinessConfigPath;
    }

    public void setBusinessConfigPath(String businessConfigPath) {
        BusinessConfigPath = businessConfigPath;
    }

    public String getValidationBase() {
        return ValidationBase;
    }

    public void setValidationBase(String validationBase) {
        ValidationBase = validationBase;
    }
}
