package com.webstudio.hub.common;

public class Constants {
    public static class SymbolTypes {
        public static int TYPE_OBJECT = 1;
        public static int TYPE_COLLECTION = 2;
        public static int TYPE_VARIABLE = 0;
    }

    public static class AttributeTypes {
        public static final String OBJECT = "object";
        public static final String COLLECTION = "collection";
        public static final String PROPERTY = "property";
    }

    public static class FileType {

    }

    public static class CommonRequestMapping {
        public static final String COMMON = "Common";
        public static final String GET_MATCHING_TABLE_NAMES = "GetMatchingTableNames";
        public static final String IS_TABLE_PRESENT = "IsTablePresent";
        public static final String GET_COLUMNS = "GetColumns";
        public static final String GET_DB_CONNECTION_INFO = "GetDBConnectionInfo";
        public static final String GET_MATCHING_FOLDERS = "GetMatchingFolders";
    }

    public static class CodeSymbolRequestMapping {
        public static final String CODE = "Code";
        public static final String GET_SYMBOLS = "GetSymbols";
        public static final String GET_OBJECT_METHODS = "GetObjectMethods";
    }

    public static class FileRequestMapping {
        public static final String FILE = "File";
        public static final String GET_FILES = "GetFiles";
    }

    public static class ProjectRequestMapping {
        public static final String PROJECT = "Project";
        public static final String LOAD_PROJECT = "LoadProject";
        public static final String REFRESH_METADATA = "RefreshMetaData";
    }

    public static class XMLRequestMapping {
        public static final String XML = "XML";
        public static final String JS_TO_XML = "JsToXml";
        public static final String XML_TO_JS = "XmlToJs";
        public static final String SAVE = "Save";
        public static final String GET_XML = "GetXml";
        public static final String CREATE_NEW_XML = "CreateNewXml";
        public static final String GET_ENTITY_FIELDS = "GetEntityFields";
        public static final String GET_FORMS = "GetForms";
        public static final String GET_NAVIGATION_PARAMETER_BY_FORM = "GetNavigationParameterByForm";
        public static final String LIST_ENTITIES = "ListEntities";
    }

    public static class Common {
        public static final String EMPTY_STRING = "";
        public static final String FOLDER_SEPARATOR = "\\";
        public static final String FULL_STOP = ".";
    }

    public static class ApplicationBeans {
        public static final String APP_CONFIG = "AppConfig";
        public static final String HUB_DATASOURCE = "HubDataSource";
        public static final String BUSINESS_DATASOURCE = "BusinessDataSource";
        public static final String HUB_DB = "HubDB";
        public static final String BUSINESS_DB = "BusinessDB";
        public static final String DEFAULT_BRANCH = "DefaultBranch";
    }


}
