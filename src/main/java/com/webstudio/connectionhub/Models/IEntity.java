package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"columns", "objects", "collections", "queries", "validation", "businessObject"})
@JacksonXmlRootElement(localName = "entity")
public class IEntity extends IBase {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String parentEntity;
    @JacksonXmlProperty(isAttribute = true)
    String tableName;
    @JacksonXmlElementWrapper(localName = "columns")
    @JacksonXmlProperty(localName = "column")
    IColumn[] columns;
    @JacksonXmlElementWrapper(localName = "objects")
    @JacksonXmlProperty(localName = "object")
    IObject[] objects;
    @JacksonXmlElementWrapper(localName = "collections")
    @JacksonXmlProperty(localName = "collection")
    ICollection[] collections;
    @JacksonXmlElementWrapper(localName = "queries")
    @JacksonXmlProperty(localName = "query")
    IQuery[] queries;
    @JacksonXmlElementWrapper(localName = "validation")
    IValidation validation;
    @JacksonXmlElementWrapper(localName = "businessObject")
    IBusinessObject businessObject;

    public IEntity(String name, String parentEntity, IColumn[] columns, IObject[] objects, ICollection[] collections, IQuery[] queries, IValidation validation, IBusinessObject businessObject) {
        this.name = name;
        this.parentEntity = parentEntity;
        this.columns = columns;
        this.objects = objects;
        this.collections = collections;
        this.queries = queries;
        this.validation = validation;
        this.businessObject = businessObject;
    }

    public IEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(String parentEntity) {
        this.parentEntity = parentEntity;
    }

    public IColumn[] getColumns() {
        return columns;
    }

    public void setColumns(IColumn[] columns) {
        this.columns = columns;
    }

    public IObject[] getObjects() {
        return objects;
    }

    public void setObjects(IObject[] objects) {
        this.objects = objects;
    }

    public ICollection[] getCollections() {
        return collections;
    }

    public void setCollections(ICollection[] collections) {
        this.collections = collections;
    }

    public IQuery[] getQueries() {
        return queries;
    }

    public void setQueries(IQuery[] queries) {
        this.queries = queries;
    }

    public IValidation getValidation() {
        return validation;
    }

    public void setValidation(IValidation validation) {
        this.validation = validation;
    }

    public IBusinessObject getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(IBusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



}
