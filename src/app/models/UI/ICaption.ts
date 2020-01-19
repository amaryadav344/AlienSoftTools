import {R} from '../../common/R';
import {IView} from './IView';

export class ICaption extends IView {
  ID: string;
  type = R.Controls.TYPE_CAPTION;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
