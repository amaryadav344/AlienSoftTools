import {IParameter} from './IParameter';
import {ICustomMap} from './ICustomMap';
import {NameCode} from '../common/NameCode';

export interface IQuery {
  expression?: string;
  label?: string;
  value?: number;
  parameters?: IParameter[];
  customMaps?: ICustomMap[];
  queryType?: NameCode;
}
