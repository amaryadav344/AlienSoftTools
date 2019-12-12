import {IViewGroup} from './IViewGroup';
import {IGrid} from './IGrid';

export class ISection extends IViewGroup {
  ID: string;
  title: string;
  grid: IGrid;

  constructor(ID: string, title: string, grid: IGrid) {
    super();
    this.ID = ID;
    this.title = title;
    this.grid = grid;
  }
}
