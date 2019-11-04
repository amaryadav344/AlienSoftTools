import {IControl} from './IControl';

export interface ILabel extends IControl {
  ID: string;
  EntityField: string;
  Visible: boolean;
  type: string;
}
