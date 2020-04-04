import {ILoadParameter} from './ILoadParameter';

export interface ILoadMapping {
  name: string;
  loadType: string;
  loadParameters: ILoadParameter[];
}
