package com.business.utils.models.Entity;

public class ISymbol {
    private String name;
    private int type;
    private String entityName;
    private String objectType;


    public ISymbol() {
    }

    public ISymbol(String name, int type, String entityName, String objectType) {
        this.name = name;
        this.type = type;
        this.entityName = entityName;
        this.objectType = objectType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}
