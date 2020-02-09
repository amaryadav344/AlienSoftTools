import {R} from '../../common/R';
import {IView} from './IView';
import {NavigationParameter} from './NavigationParameter';

export class IButton extends IView {
  ID: string;
  text = '';
  onClick: string;
  method: string;
  navigationParameters: NavigationParameter[] = [{name: 'aaa', value: '', isConstant: false}];
  form: string;
  type = R.Controls.TYPE_BUTTON;


  constructor(ID: string) {
    super();
    this.ID = ID;

  }
}
