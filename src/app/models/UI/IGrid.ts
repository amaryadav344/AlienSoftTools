import {IGRow} from './IGRow';
import {R} from '../../common/R';

export class IGrid {
  rows: IGRow[];
  ID: string;
  type = R.Controls.TYPE_GRID;


  constructor(rows: IGRow[], ID: string) {
    this.rows = rows;
    this.ID = ID;
  }
}
