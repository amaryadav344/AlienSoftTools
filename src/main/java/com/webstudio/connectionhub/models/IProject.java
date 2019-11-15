package com.webstudio.connectionhub.models;

public class IProject {
    String name;
    String XMLPath;
    String BinPath;
    String PackageName;
    String BusinessModelPath;

    public IProject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXMLPath() {
        return XMLPath;
    }

    public void setXMLPath(String XMLPath) {
        this.XMLPath = XMLPath;
    }

    public String getBinPath() {
        return BinPath;
    }

    public void setBinPath(String binPath) {
        BinPath = binPath;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getBusinessModelPath() {
        return BusinessModelPath;
    }

    public void setBusinessModelPath(String businessModelPath) {
        BusinessModelPath = businessModelPath;
    }
}
