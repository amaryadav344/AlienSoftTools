import {IXMLBase} from '../IXMLBase';
import {IAttribute} from './IAttribute';

export interface IEntity extends IXMLBase {
  name: string;
  type: string;
  businessObject: string;
  isWrapper: boolean;
  tableName: string;
  serviceName: string;
  attributes: IAttribute[];
}
