import {R} from '../../common/R';

export class ICaption {
  ID: string;
  type = R.Controls.TYPE_CAPTION;

  constructor(ID: string) {
    this.ID = ID;
  }
}
