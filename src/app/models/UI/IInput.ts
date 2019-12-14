import {IView} from './IView';

export class IInput extends IView {
  ID: string;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
