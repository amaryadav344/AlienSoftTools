import {R} from '../../common/R';
import {IView} from './IView';

export class ILabel extends IView {
  ID: string;
  letterSpacing: number;
  lineHeight: number;
  text: string;
  textAlignment: string;
  textDecoration: string;
  textTransform: string;
  textWrap: boolean;
  whiteSpace: string;
  entityField: string;
  type = R.Controls.TYPE_LABEL;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
