import {IXMLBase} from '../IXMLBase';

export class IForm extends IXMLBase {
  control: any;
  type = 'form';
  entity: string;
  name: string;


  constructor(control: any, entity: string, name: string) {
    super();
    this.control = control;
    this.entity = entity;
    this.name = name;
  }
}
