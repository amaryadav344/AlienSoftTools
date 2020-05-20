import {IXMLBase} from '../IXMLBase';
import {ILoadDetails} from './ILoadDetails';

export class IForm extends IXMLBase {
  control: any;
  type = 'form';
  entity: string;
  name: string;
  loadDetails: ILoadDetails


  constructor(control: any, entity: string, name: string, loadDetails: ILoadDetails) {
    super();
    this.control = control;
    this.entity = entity;
    this.name = name;
    this.loadDetails = this.loadDetails;
  }
}
