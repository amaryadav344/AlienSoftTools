import {IVRule} from './IVRule';
import {IGroup} from './IGroup';
import {IRule} from './IRule';

export interface IValidation {
  rules: IVRule[];
  initialLoad: IRule[];
  softErrors: IRule[];
  hardErrors: IRule[];
  updateRules: IRule[];
  deleteRules: IRule[];
  groupRules: IGroup[];
}
