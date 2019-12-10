import {IView} from './IView';

export class ILabel extends IView {
  ID: string;

  constructor(ID: string) {
    super();
    this.ID = ID;
  }
}
