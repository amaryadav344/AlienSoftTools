import {IForm} from '../models/UI/IForm';

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

}
