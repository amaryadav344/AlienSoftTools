import {R} from '../../common/R';

export class IInput {
  ID: string;
  type = R.Controls.TYPE_INPUT;

  constructor(ID: string) {
    this.ID = ID;
  }
}
