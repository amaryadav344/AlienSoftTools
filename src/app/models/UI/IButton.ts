import {IView} from './IView';

export class IButton extends IView {
  ID: string;


  constructor(ID: string) {
    super();
    this.ID = ID;

  }
}
