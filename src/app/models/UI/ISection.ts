import {IGrid} from './IGrid';
import {R} from '../../common/R';

export class ISection {
  ID: string;
  title: string;
  grid: IGrid;
  type = R.Controls.TYPE_SECTION;

  constructor(ID: string, title: string, grid: IGrid) {
    this.ID = ID;
    this.title = title;
    this.grid = grid;
  }
}
