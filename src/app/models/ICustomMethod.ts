import {ILoadMapping} from './ILoadMapping';
import {NameCode} from '../common/NameCode';

export interface ICustomMethod {
  name: string;
  mode: NameCode;
  loadPrimaryObject: boolean;
  loadMapping: ILoadMapping[];
}
