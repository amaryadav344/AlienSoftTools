import {ITable} from './ITable';
import {IControl} from './IControl';

export interface IPanel extends IControl {
  ID: string;
  name: string;
  table: ITable;
  collapsible: boolean;
}
