import {R} from '../../common/R';
import {IView} from './IView';

export class IInput extends IView {
  ID: string;
  type = R.Controls.TYPE_INPUT;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
