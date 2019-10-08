import {IObjectParameter} from './IObjectParameter';

export interface IObjectMethod {
  name: string;
  returnType: string;
  objectParameters: IObjectParameter[];
}
