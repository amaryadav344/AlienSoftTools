import {IGColumn} from './IGColumn';
import {IViewGroup} from './IViewGroup';

export class IGRow extends IViewGroup {
  columns: IGColumn[];


  constructor(columns: IGColumn[]) {
    super();
    this.columns = columns;
  }
}
