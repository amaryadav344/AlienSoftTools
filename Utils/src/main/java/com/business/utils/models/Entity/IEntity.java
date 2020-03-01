package com.business.utils.models.Entity;

import com.business.utils.models.IXMLBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JsonPropertyOrder({"columns", "property", "objects", "collections", "queries", "validation", "businessObject"})
@JacksonXmlRootElement(localName = "entity")
@JsonIgnoreProperties({"type"})
public class IEntity extends IXMLBase {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    String type;
    @JacksonXmlProperty(isAttribute = true)
    String parentEntity;
    @JacksonXmlProperty(isAttribute = true)
    String modelName;
    @JacksonXmlProperty(isAttribute = true)
    boolean isWrapper;
    @JacksonXmlProperty(isAttribute = true)
    String databaseObjectField;
    @JacksonXmlProperty(isAttribute = true)
    String tableName;
    @JacksonXmlElementWrapper(localName = "columns")
    @JacksonXmlProperty(localName = "column")
    IColumn[] columns;
    @JacksonXmlElementWrapper(localName = "properties")
    @JacksonXmlProperty(localName = "property")
    IProperty[] properties;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDatabaseObjectField() {
        return databaseObjectField;
    }

    public void setDatabaseObjectField(String databaseObjectField) {
        this.databaseObjectField = databaseObjectField;
    }

    public IProperty[] getProperties() {
        return properties;
    }

    public void setProperties(IProperty[] properties) {
        this.properties = properties;
    }

    public boolean getIsWrapper() {
        return isWrapper;
    }

    public void setWrapper(boolean wrapper) {
        isWrapper = wrapper;
    }
}
