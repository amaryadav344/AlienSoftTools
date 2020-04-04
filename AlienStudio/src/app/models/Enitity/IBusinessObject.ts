import {ICustomMethod} from './ICustomMethod';
import {IObjectMethod} from './IObjectMethod';

export interface IBusinessObject {
  customMethods: ICustomMethod[];
  objectMethods: IObjectMethod[];
}
