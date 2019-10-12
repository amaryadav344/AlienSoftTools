import {IParameter} from './IParameter';
import {ICustomMap} from './ICustomMap';

export interface IQuery {
  expression?: string;
  label?: string;
  value?: string;
  parameters?: IParameter[];
  customMaps?: ICustomMap[];
  queryType?: string;
}
