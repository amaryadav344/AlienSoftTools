import {R} from '../../common/R';

export class StackLayout {
  controls: any[];
  ID: string;
  type = R.Controls.TYPE_STACK_LAYOUT;


  constructor(controls: any[], ID: string) {
    this.controls = controls;
    this.ID = ID;
  }
}
