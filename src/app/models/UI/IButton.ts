import {R} from '../../common/R';
import {IView} from './IView';

export class IButton extends IView {
  ID: string;
  text = '';
  type = R.Controls.TYPE_BUTTON;


  constructor(ID: string) {
    super();
    this.ID = ID;

  }
}
