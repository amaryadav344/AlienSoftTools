import {R} from '../../common/R';
import {IView} from './IView';

export class StackLayout extends IView {
  controls: any[];
  ID: string;
  orientation: string;
  type = R.Controls.TYPE_STACK_LAYOUT;


  constructor(controls: any[], ID: string) {
    super();
    this.controls = controls;
    this.ID = ID;
  }
}
