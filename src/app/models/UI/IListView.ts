import {IView} from './IView';
import {R} from '../../common/R';

export class IListView extends IView {
  control: any;
  ID: string;
  type = R.Controls.TYPE_LIST_VIEW;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }

}
