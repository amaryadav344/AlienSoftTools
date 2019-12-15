import {R} from '../../common/R';

export class IButton {
  ID: string;
  type = R.Controls.TYPE_BUTTON;


  constructor(ID: string) {
    this.ID = ID;

  }
}
