import {R} from '../../common/R';

export class ILabel {
  ID: string;
  type = R.Controls.TYPE_LABEL;

  constructor(ID: string) {
    this
      .ID = ID;
  }
}
