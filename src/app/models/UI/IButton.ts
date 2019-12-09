import {IControl} from './IControl';

export class IButton extends IControl {
  ID: string;


  constructor(ID: string) {
    super();
    this.ID = ID;

  }
}
