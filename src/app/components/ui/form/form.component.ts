import {Component, OnInit} from '@angular/core';
import {R} from '../../../common/R';
import {IForm} from '../../../models/UI/IForm';
import {IControl} from '../../../models/UI/IControl';
import {IButton} from '../../../models/UI/IButton';
import {ICheckBox} from '../../../models/UI/ICheckBox';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  form: IForm = R.Initializer.getForm();

  constructor() {

  }

  ngOnInit() {

  }

  calculateClasses(control: IControl) {
    let className = [];
    if (control instanceof IButton) {
      className = ['pi', 'pi-check-square'];
    } else if (control instanceof ICheckBox) {
      className = ['pi', 'pi-play'];
    }
    return className;
  }

}
