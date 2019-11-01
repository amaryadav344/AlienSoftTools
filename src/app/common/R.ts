import {ICollection} from '../models/ICollection';
import {IColumn} from '../models/IColumn';
import {ICustomMethod} from '../models/ICustomMethod';
import {ILoadMapping} from '../models/ILoadMapping';
import {IEntity} from '../models/IEntity';
import {IGroup} from '../models/IGroup';
import {IObject} from '../models/IObject';
import {IObjectMethod} from '../models/IObjectMethod';
import {IQuery} from '../models/IQuery';
import {IVRule} from '../models/IVRule';
import {IFile} from '../models/IFile';

export class R {

  public static QueryTypes: string[] = ['Select Query',
    'Scalar Query',
    'Sub Query',
  ];
  static DataTypes: string[] = [
    'String',
    'Integer',
    'DateTime',
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
    static SAVE_XML = 'http://localhost:8080/xml/save';
    static XML_TO_JS = 'http://localhost:8080/xml/xmltojs';
    static GET_FILE = 'http://localhost:8080/xml/getxml';
    static GET_MATCHING_FOLDERS = 'http://localhost:8080/xml/getMatchingFolders';
    static GET_MATCHING_TABLES = 'http://localhost:8080/xml/getMatchingTableNames';
    static GET_COLUMNS = 'http://localhost:8080/xml/getColumns';
    static CREATE_NEW_XML = 'http://localhost:8080/xml/CreateNewXml/';
    static GET_FILES = 'http://localhost:8080/xml/GetFiles';
    static GET_SYMBOLS = 'http://localhost:8080/xml/getSymbols';
  };

  static Initializer = class {
    static getCollection(): ICollection {
      return {entity: '', name: '', objectField: '', dataType: ''};
    }

    static getColumn(): IColumn {
      return {name: '', maxLength: '', canBeNull: '', dataType: '', objectField: ''};
    }

    static getCustomMethod(): ICustomMethod {
      return {name: '', mode: 'All', loadPrimaryObject: false, loadMapping: []};
    }

    static getLoadMapping(): ILoadMapping {
      return {name: '', loadParameters: [], loadType: ''};
    }

    static getEntity(): IEntity {
      return {
        name: '', modelName: '', databaseObjectField: '', type: 'entity', parentEntity: '', tableName: '', validation: {
          rules: [], groupRules: [], hardErrors: [], softErrors: [], initialLoad: [], updateRules: [], deleteRules: []
        },
        queries: [], collections: [], objects: [], columns: [], businessObject: {customMethods: [], objectMethods: []}
      };
    }

    static getGroup(): IGroup {
      return {name: '', rules: []};
    }

    static getObject(): IObject {
      return {name: '', entity: '', objectField: ''};
    }

    static getObjectMethod(): IObjectMethod {
      return {name: '', returnType: '', objectParameters: []};
    }

    static getQuery(): IQuery {
      return {name: '', sql: '', queryType: ''};
    }

    static getVRule(): IVRule {
      return {expression: '', name: '', message: {message: '', messageId: 0, parameters: []}};
    }

    static getFile(): IFile {
      return {name: '', type: 0, path: ''};
    }
  };
}

