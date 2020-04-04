import {R} from '../../common/R';

export class GridLayout {
  controls: any[];
  ID: string;
  columns: string;
  rows: string;
  type = R.Controls.TYPE_GRID_LAYOUT;


  constructor(controls: any[], ID: string) {
    this.controls = controls;
    this.ID = ID;
  }
}
