export class R {

  public static QueryTypes: string[] = ['Select Query',
    'Scalar Query',
    'Sub Query',
  ];
  static DataTypes: string[] = [
    'String',
    'Integer',
    'Datetime',
    'Decimal',
  ];
  static CollectionTypes: string[] = [
    'List',
    'Queue',
    'Set',
  ];
  static LoadTypes: string[] = [
    'Query',
    'Method',
    'Foreign Key',
    'Rule',
  ];
  static LoadModes: string[] = [
    'New',
    'Update',
    'All'
  ];
  static Constants = class {
    static OpenMode = class {
      static MODE_NEW = 0;
      static MODE_UPDATE = 1;
      static MODE_ALL = 2;
    };
  };
  static SERVER_URLS = class {
    static JS_TO_XML = 'http://localhost:8080/xml/jstoxml';
    static XML_TO_JS = 'http://localhost:8080/xml/xmltojs';
    static GET_FILE = 'http://localhost:8080/xml/getxml';
    static GET_MATCHING_FOLDERS = 'http://localhost:8080/xml/getMatchingFolders';
    static GET_MATCHING_TABLES = 'http://localhost:8080/xml/getMatchingTableNames';
    static GET_COLUMNS = 'http://localhost:8080/xml/getColumns';
    static CREATE_NEW_XML = 'http://localhost:8080/xml/CreateNewXml/';
    static GET_FILES = 'http://localhost:8080/xml/GetFiles';
  };
}

