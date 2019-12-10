import {IView} from './IView';

export class ICaption extends IView {
  ID: string;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
