import {IParameter} from './IParameter';
import {ICustomMap} from './ICustomMap';

export interface IQuery {
  sql?: string;
  name?: string;
  parameters?: IParameter[];
  customMaps?: ICustomMap[];
  queryType?: string;
}
