import {NameCode} from './NameCode';

export class R {

  public static QueryTypes: NameCode[] = [
    {name: 'Select Query', code: 'SEQ'},
    {name: 'Scalar Query', code: 'SCQ'},
    {name: 'Sub Query', code: 'SUQ'},
  ];
  static DataTypes: NameCode[] = [
    {name: 'String', code: 'STR'},
    {name: 'Integer', code: 'INT'},
    {name: 'Datetime', code: 'DAT'},
    {name: 'Decimal', code: 'DEC'},
  ];
  static CollectionTypes: NameCode[] = [
    {name: 'List', code: 'LST'},
    {name: 'Queue', code: 'QUE'},
    {name: 'Set', code: 'SET'},
  ];
  static LoadTypes: NameCode[] = [
    {name: 'Query', code: 'QER'},
    {name: 'Method', code: 'MED'},
    {name: 'Foreign Key', code: 'FK'},
    {name: 'Rule', code: 'RU'},
  ];
  static LoadModes: NameCode[] = [
    {name: 'New', code: 'NEW'},
    {name: 'Update', code: 'UPD'},
    {name: 'All', code: 'ALL'},
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
  };

  static getCollectionTypesCodeByName(name: string): NameCode {
    return R.CollectionTypes.filter(x => x.name = name)[0];
  }
}

