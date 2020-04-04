import {R} from '../../common/R';
import {IView} from './IView';

export class ICheckBox extends IView {
  ID: string;
  type = R.Controls.TYPE_CHECKBOX;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
