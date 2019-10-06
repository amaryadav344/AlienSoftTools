import {NameCode} from '../common/NameCode';
import {ILoadParameter} from './ILoadParameter';

export interface ILoadMapping {
  name: string;
  loadType: NameCode;
  loadParameters: ILoadParameter[];
}
