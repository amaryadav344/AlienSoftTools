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

  static Constants = class {
    static OpenMode = class {
      static MODE_NEW = 0;
      static MODE_UPDATE = 1;
    };
  };
}

