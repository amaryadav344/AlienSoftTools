import {IView} from './IView';
import {R} from '../../common/R';

export class ScrollView extends IView {
  orientation: string;
  control: any;
  ID: string;
  type = R.Controls.TYPE_SCROLL_VIEW;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
