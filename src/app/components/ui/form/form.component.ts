import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {R} from '../../../common/R';
import {IForm} from '../../../models/UI/IForm';
import {IButton} from '../../../models/UI/IButton';
import {ICheckBox} from '../../../models/UI/ICheckBox';
import {ILabel} from '../../../models/UI/ILabel';
import {ICaption} from '../../../models/UI/ICaption';
import {IInput} from '../../../models/UI/IInput';
import {DragDropHelper} from '../../../common/DragDropHelper';
import {StackLayout} from '../../../models/UI/StackLayout';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  @Input()
  form: IForm;
  selection: any;
  preventSingleClick = false;
  timer: any;
  delay: number;
  @Output() openPropertiesTab = new EventEmitter();
  @Output() openProperties = new EventEmitter<any>();
  dragDropHelper: DragDropHelper = DragDropHelper.getInstance();

  constructor() {

  }

  ngOnInit() {

  }

  calculateClasses(control: any) {
    let className = [];
    if (control.type === R.Controls.TYPE_BUTTON) {
      className = ['pi', 'pi-play'];
    } else if (control.type === R.Controls.TYPE_CHECKBOX) {
      className = ['pi', 'pi-check-square'];
    } else if (control.type === R.Controls.TYPE_LABEL) {
      className = ['fa', 'fa-language'];
    } else if (control.type === R.Controls.TYPE_CAPTION) {
      className = ['fa', 'fa-tag'];
    } else if (control.type === R.Controls.TYPE_INPUT) {
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

  selectControl(event, view: any) {
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

  allowDrop(event, Control) {
    if (Control.type === R.Controls.TYPE_STACK_LAYOUT) {
      event.preventDefault();
    } else {
      event.stopPropagation();

    }
  }

  GetViewGroupType(ViewGroup: any) {
    if (ViewGroup.type === R.Controls.TYPE_GRID) {
      return 0;
    } else if (ViewGroup.type === R.Controls.TYPE_SECTION) {
      return 1;
    }
  }

  dropControl(event, layout: any) {
    event.stopPropagation();
    const Control = this.dragDropHelper.getControl();
    switch (Control) {
      case R.Controls.TYPE_LABEL:
        layout.controls.push(new ILabel('lblLabel3'));
        break;
      case R.Controls.TYPE_BUTTON:
        layout.controls.push(new IButton('btn123'));
        break;
      case R.Controls.TYPE_CAPTION:
        layout.controls.push(new ICaption('lblLabel3'));
        break;
      case R.Controls.TYPE_CHECKBOX:
        layout.controls.push(new ICheckBox('lblLabel3'));
        break;
      case R.Controls.TYPE_INPUT:
        layout.controls.push(new IInput('lblLabel3'));
        break;
      case R.Controls.TYPE_STACK_LAYOUT:
        layout.controls.push(new StackLayout([], 'stkone'));
        break;
    }
  }

  singleClick(event, view: any) {
    event.stopPropagation();
    this.preventSingleClick = false;
    const delay = 200;
    this.timer = setTimeout(() => {
      if (!this.preventSingleClick) {
        this.selectControl(event, view);

      }
    }, delay);
  }

  doubleClick(event, view: any) {
    this.preventSingleClick = true;
    clearTimeout(this.timer);
    this.selectControl(event, view);
    this.openPropertiesTab.emit();
    event.stopPropagation();
  }

  exapand(event) {
  }

}
