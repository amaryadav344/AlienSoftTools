import {IXMLBase} from '../IXMLBase';

export class IForm extends IXMLBase {
  control: any;
  type = 'form';
  entity: string;
  ID: string;


  constructor(control: any, entity: string, ID: string) {
    super();
    this.control = control;
    this.entity = entity;
    this.ID = ID;
  }
}
