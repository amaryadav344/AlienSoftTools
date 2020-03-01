import {IForm} from '../models/UI/IForm';
import {R} from "./R";

export class DynamicIDGenerator {
  ID: string[] = [];

  private constructor(form: IForm) {
    this.ID.push(form.control.ID);
    if (form.control.controls != null) {
      this.registerChilds(form.control.controls);
    }
  }

  public static getFor(form: IForm) {
    return new DynamicIDGenerator(form);
  }

  registerChilds(Controls: any[]) {
    for (let i = 0; i < Controls.length; i++) {
      this.ID.push(Controls[i].ID);
      if (Controls[i].controls) {
        this.registerChilds(Controls[i].controls);
      }
    }

  }

  getNextID(control: string, index: number) {
    let ID = '';
    switch (control) {
      case R.Controls.TYPE_STACK_LAYOUT:
        ID = R.Controls.TYPE_STACK_LAYOUT + index;
        break;
      case R.Controls.TYPE_INPUT:
        ID = R.Controls.TYPE_INPUT + index;
        break;
      case R.Controls.TYPE_CAPTION:
        ID = R.Controls.TYPE_CAPTION + index;
        break;
      case R.Controls.TYPE_LABEL:
        ID = R.Controls.TYPE_LABEL + index;
        break;
      case R.Controls.TYPE_CHECKBOX:
        ID = R.Controls.TYPE_CHECKBOX + index;
        break;
      case R.Controls.TYPE_BUTTON:
        ID = R.Controls.TYPE_BUTTON + index;
        break;
      case R.Controls.TYPE_SECTION:
        ID = R.Controls.TYPE_SECTION + index;
        break;
      case R.Controls.TYPE_GRID:
        ID = R.Controls.TYPE_GRID + index;
        break;
      case R.Controls.TYPE_GRID_LAYOUT:
        ID = R.Controls.TYPE_GRID_LAYOUT + index;
        break;
      case R.Controls.TYPE_SCROLL_VIEW:
        ID = R.Controls.TYPE_SCROLL_VIEW + index;
        break;
      case R.Controls.TYPE_LIST_VIEW:
        ID = R.Controls.TYPE_LIST_VIEW + index;
        break;
    }
    if (this.ID.filter(x => x === ID).length > 0) {
      return this.getNextID(control, index + 1);
    }
    this.ID.push(ID);
    return ID;
  }

}
