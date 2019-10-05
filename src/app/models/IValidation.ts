import {IVRule} from './IVRule';
import {IGroup} from './IGroup';

export interface IValidation {
  rules: IVRule[];
  groups: IGroup[];
}
