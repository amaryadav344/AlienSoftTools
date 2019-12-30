import {IForm} from '../models/UI/IForm';

export class DynamicIDGenerator {
  ID: string[] = [];

  private constructor(form: IForm) {
    this.ID.push(form.control.ID);
    this.registerChilds(form.control.controls);
  }

  public static getFor(form: IForm) {
    return new DynamicIDGenerator(form);
  }

  registerChilds(Controls: any[]) {
    for (const Control of Controls) {
      this.ID.push(Control.ID);
      if (Control.controls) {
        this.registerChilds(Control.controls);
      }
    }

  }

}
