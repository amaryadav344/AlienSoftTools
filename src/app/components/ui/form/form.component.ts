import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {R} from '../../../common/R';
import {IForm} from '../../../models/UI/IForm';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FormComponent implements OnInit {

  form: IForm = R.Initializer.getForm();

  constructor() {

  }

  ngOnInit() {
  }

  isLabel(control: any) {
    return control.label;
  }

}
