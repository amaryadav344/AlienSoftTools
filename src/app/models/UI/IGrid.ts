import {IGRow} from './IGRow';
import {IViewGroup} from './IViewGroup';

export class IGrid extends IViewGroup {
  rows: IGRow[];
  ID: string;


  constructor(rows: IGRow[], ID: string) {
    super();
    this.rows = rows;
    this.ID = ID;
  }
}
