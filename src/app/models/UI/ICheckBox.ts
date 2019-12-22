import {R} from '../../common/R';

export class ICheckBox {
  ID: string;
  type = R.Controls.TYPE_CHECKBOX;

  constructor(ID: string) {
    this.ID = ID;
  }
}
