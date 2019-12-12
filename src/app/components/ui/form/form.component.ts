import {Component, OnInit} from '@angular/core';
import {R} from '../../../common/R';
import {IForm} from '../../../models/UI/IForm';
import {IView} from '../../../models/UI/IView';
import {IButton} from '../../../models/UI/IButton';
import {ICheckBox} from '../../../models/UI/ICheckBox';
import {IViewGroup} from "../../../models/UI/IViewGroup";
import {IGrid} from "../../../models/UI/IGrid";
import {ILabel} from "../../../models/UI/ILabel";
import {ICaption} from "../../../models/UI/ICaption";
import {ISection} from "../../../models/UI/ISection";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  form: IForm = R.Initializer.getForm();
  selection: any;

  constructor() {

  }

  ngOnInit() {

  }

  calculateClasses(control: IView) {
    let className = [];
    if (control instanceof IButton) {
      className = ['pi', 'pi-play'];
    } else if (control instanceof ICheckBox) {
      className = ['pi', 'pi-check-square'];
    } else if (control instanceof ILabel) {
      className = ['fa', 'fa-language'];
    } else if (control instanceof ICaption) {
      className = ['fa', 'fa-tag'];
    }
    return className;
  }


  removeControlSelection() {
    if (this.selection != null && this.selection.classList.contains('selected')) {
      this.selection.classList.remove('selected');
    }
    this.selection = null;
  }

  selectControl(event) {
    const clicked = event.target;
    const currentID = clicked.id || null;
    if (this.selection != null && !(this.selection.id === currentID)) {
      this.removeControlSelection();
    }
    if (currentID != null) {
      clicked.classList.add('selected');
      this.selection = clicked;
    }
    event.stopPropagation();
  }

  GetViewGroupType(ViewGroup: IViewGroup) {
    if (ViewGroup instanceof IGrid) {
      return 0;
    } else if (ViewGroup instanceof ISection) {
      return 1;
    }
  }

}
