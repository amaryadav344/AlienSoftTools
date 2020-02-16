package com.business.utils.models.Entity;

public class IFile {
    String path;
    int type;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IFile() {
    }

    public IFile(String path, int type, String name) {
        this.path = path;
        this.type = type;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
