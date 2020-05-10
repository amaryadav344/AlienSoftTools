import {IEntity} from '../models/Enitity/IEntity';
import {IFile} from '../models/Enitity/IFile';
import {IDBConnectionInfo} from '../models/Enitity/IDBConnectionInfo';
import {WindowItem} from './window-Item';
import {IForm} from '../models/UI/IForm';
import {ISymbol} from '../models/Enitity/ISymbol';
import {StackLayout} from '../models/UI/StackLayout';
import {ICustomMethod} from '../models/Enitity/ICustomMethod';
import {ILoadMapping} from '../models/Enitity/ILoadMapping';

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
  static TextAlignments: string[] = [
    'initial',
    'left',
    'center',
    'right'
  ];
  static TextDecorations: string[] = [
    'none',
    'underline',
    'line-through',
  ];
  static TextTransforms: string[] = [
    'initial',
    'none',
    'capitalize',
    'uppercase',
    'lowercase',
  ];
  static WhiteSpaces: string[] = [
    'initial',
    'normal',
    'nowrap',
  ];
  static BoolenValues: string[] = [
    'true',
    'false',
  ];
  static Orientations: string[] = [
    'vertical',
    'horizontal',
  ];
  static VerticalAlignments: string[] = [
    'top',
    'center',
    'bottom',
    'stretch',
  ];
  static HorizontalAlignments: string[] = [
    'left',
    'center',
    'right',
    'stretch',
  ];
  static Visibility: string[] = [
    'visible',
    'collapse',
    'hidden',
  ];

  static ButtonTypes: string[] = [
    'btnOpenFormClick',
    'btnExecuteBusinessMethod'
  ];


  static Constants = class {
    static OpenMode = class {
      static MODE_NEW = 0;
      static MODE_UPDATE = 1;
      static MODE_ALL = 2;
    };
  };
  static SymbolTypes = class {
    static TYPE_OBJECT = 1;
    static TYPE_COLLECTION = 2;
    static TYPE_VARIBLE = 0;
  };
  static SERVER_URLS = class {
    static JS_TO_XML = 'http://localhost:8080/XML/JsToXml';
    static SAVE_XML = 'http://localhost:8080/XML/Save';
    static XML_TO_JS = 'http://localhost:8080/XML/XmlToJs';
    static GET_FILE = 'http://localhost:8080/XML/GetXml';
    static GET_MATCHING_FOLDERS = 'http://localhost:8080/Common/GetMatchingFolders';
    static GET_MATCHING_TABLES = 'http://localhost:8080/Common/GetMatchingTableNames';
    static GET_COLUMNS = 'http://localhost:8080/Common/GetColumns';
    static CREATE_NEW_XML = 'http://localhost:8080/XML/CreateNewXml/';
    static GET_FILES = 'http://localhost:8080/File/GetFiles';
    static GET_FORMS = 'http://localhost:8080/XML/GetForms';
    static GET_NAVIGATION_PARAMETER_BY_FORM = 'http://localhost:8080/xml/GetNavigationParameterByForm';
    static GET_SYMBOLS = 'http://localhost:8080/Code/GetSymbols';
    static GET_ENTITY_FIELDS = 'http://localhost:8080/XML/getEntityFields';
    static GET_DB_CONNECTION_INFO = 'http://localhost:8080/Common/GetDBConnectionInfo';
    static LOAD_PROJECT = 'http://localhost:8080/Project/LoadProject';
    static GET_OBJECT_METHODS = 'http://localhost:8080/Code/GetObjectMethods';
    static LIST_ENTITIES = 'http://localhost:8080/XML/ListEntities';
    static REFRESH_META_DATA = 'http://localhost:8080/Project/RefreshMetaData';
  };

  static Initializer = class {


    static getCustomMethod(): ICustomMethod {
      return {name: '', mode: 'All', loadPrimaryObject: false, loadMapping: []};
    }

    static getLoadMapping(): ILoadMapping {
      return {name: '', loadParameters: [], loadType: ''};
    }

    static getEntity(): IEntity {
      return {
        name: '',
        type: 'entity',
        businessObject: '',
        isWrapper: false,
        tableName: '',
        serviceName: '',
        attributes: [],
      };
    }

    static getFile(): IFile {
      return {name: '', type: 0, path: ''};
    }

    static getDBConnectionInfo(): IDBConnectionInfo {
      return {DBUrl: '', DBUserName: ''};
    }

    static getWindowItem(): WindowItem {
      return {component: null, data: R.Initializer.getFile()};
    }

    static getForm(): IForm {
      return new IForm(new StackLayout([], 'stackLayout'), '', '', R.Initializer.getCustomMethod());
    }

    static getSymbols(): ISymbol[] {
      return [{name: 'person', entityName: 'personName', type: R.SymbolTypes.TYPE_VARIBLE},
        {name: 'person', entityName: 'personName', type: R.SymbolTypes.TYPE_VARIBLE}];
    }
  };
  static Controls = class {
    static TYPE_LABEL = 'Label';
    static TYPE_CAPTION = 'Caption';
    static TYPE_INPUT = 'Input';
    static TYPE_BUTTON = 'Button';
    static TYPE_CHECKBOX = 'Checkbox';
    static TYPE_SECTION = 'Section';
    static TYPE_GRID = 'Grid';
    static TYPE_STACK_LAYOUT = 'StackLayout';
    static TYPE_GRID_LAYOUT = 'GridLayout';
    static TYPE_SCROLL_VIEW = 'ScrollView';
    static TYPE_LIST_VIEW = 'ListView';
  };

}

