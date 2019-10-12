import {ILoadMapping} from './ILoadMapping';

export interface ICustomMethod {
  name: string;
  mode: string;
  loadPrimaryObject: boolean;
  loadMapping: ILoadMapping[];
}
