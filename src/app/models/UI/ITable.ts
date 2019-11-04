import {IControl} from './IControl';
import {ITRow} from './ITRow';

export interface ITable extends IControl {
  rows: ITRow[];
}
