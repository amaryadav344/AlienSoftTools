import {IGColumn} from './IGColumn';

export class IGRow {
  columns: IGColumn[];


  constructor(columns: IGColumn[]) {
    this.columns = columns;
  }
}
