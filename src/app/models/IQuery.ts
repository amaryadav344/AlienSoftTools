import {IParameter} from './IParameter';
import {ICustomMap} from './ICustomMap';

export interface IQuery {
  expression: string;
  label: string;
  value: number;
  parameters: IParameter[];
  customMaps: ICustomMap[];
}
