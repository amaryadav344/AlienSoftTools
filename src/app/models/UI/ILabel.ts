import {R} from '../../common/R';

export class ILabel {
  ID: string;
  letterSpacing: number;
  lineHeight: number;
  text = '';
  textAlignment = '';
  textDecoration = '';
  textTransform = '';
  textWrap: boolean;
  whiteSpace = '';
  entityField = '';
  width: number;
  height: number;
  backgroundColor: string;
  verticalAlignment: string;
  horizontalAlignment: string;
  type = R.Controls.TYPE_LABEL;

  constructor(ID: string) {
    this.ID = ID;
  }
}
