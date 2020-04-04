import {IXMLBase} from '../IXMLBase';
import {ICustomMethod} from '../Enitity/ICustomMethod';

export class IForm extends IXMLBase {
  control: any;
  type = 'form';
  entity: string;
  name: string;
  loadMethod: ICustomMethod;


  constructor(control: any, entity: string, name: string, loadMethod) {
    super();
    this.control = control;
    this.entity = entity;
    this.name = name;
    this.loadMethod = loadMethod;
  }
}
