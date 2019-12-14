import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {R} from '../../../common/R';
import {IForm} from '../../../models/UI/IForm';
import {IView} from '../../../models/UI/IView';
import {IButton} from '../../../models/UI/IButton';
import {ICheckBox} from '../../../models/UI/ICheckBox';
import {IViewGroup} from '../../../models/UI/IViewGroup';
import {IGrid} from '../../../models/UI/IGrid';
import {ILabel} from '../../../models/UI/ILabel';
import {ICaption} from '../../../models/UI/ICaption';
import {ISection} from '../../../models/UI/ISection';
import {IInput} from '../../../models/UI/IInput';
import {IGColumn} from '../../../models/UI/IGColumn';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  form: IForm = R.Initializer.getForm();
  selection: any;
  preventSingleClick = false;
  timer: any;
  delay: number;
  @Output() openPropertiesTab = new EventEmitter();
  @Output() openProperties = new EventEmitter<IView>();

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
    } else if (control instanceof IInput) {
      className = ['fa', 'fa-i-cursor'];
    }
    return className;
  }


  removeControlSelection() {
    if (this.selection != null && this.selection.classList.contains('selected')) {
      this.selection.classList.remove('selected');
    }
    this.selection = null;
  }

  selectControl(event, view: IView) {
    const clicked = event.target;
    const currentID = clicked.id || null;
    if (this.selection != null && !(this.selection.id === currentID)) {
      this.removeControlSelection();
    }
    if (currentID != null) {
      clicked.classList.add('selected');
      this.selection = clicked;
    }
    this.openProperties.emit(view);

  }

  GetViewGroupType(ViewGroup: IViewGroup) {
    if (ViewGroup instanceof IGrid) {
      return 0;
    } else if (ViewGroup instanceof ISection) {
      return 1;
    }
  }

  dropControl(event, column: IGColumn) {
    const id = event.dataTransfer.getData('Control');
    switch (id) {
      case R.Controls.TYPE_LABEL:
        column.controls.push(new ILabel('lblLabel3'));
        break;
      case R.Controls.TYPE_BUTTON:
        column.controls.push(new IButton('lblLabel3'));
        break;
      case R.Controls.TYPE_CAPTION:
        column.controls.push(new ICaption('lblLabel3'));
        break;
      case R.Controls.TYPE_CHECKBOX:
        column.controls.push(new ICheckBox('lblLabel3'));
        break;
      case R.Controls.TYPE_INPUT:
        column.controls.push(new IInput('lblLabel3'));
        break;
    }
  }

  singleClick(event, view: IView) {
    event.stopPropagation();
    this.preventSingleClick = false;
    const delay = 200;
    this.timer = setTimeout(() => {
      if (!this.preventSingleClick) {
        this.selectControl(event, view);

      }
    }, delay);
  }

  doubleClick(event, view: IView) {
    this.preventSingleClick = true;
    clearTimeout(this.timer);
    this.selectControl(event, view);
    this.openPropertiesTab.emit();
    event.stopPropagation();
  }

}
