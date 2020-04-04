import {IColumn} from './IColumn';
import {IObject} from './IObject';
import {ICollection} from './ICollection';
import {IQuery} from './IQuery';
import {IValidation} from './IValidation';
import {IBusinessObject} from './IBusinessObject';
import {IXMLBase} from '../IXMLBase';
import {IProperty} from './IProperty';

export interface IEntity extends IXMLBase {
  name: string;
  type: string;
  modelName: string;
  databaseObjectField: string;
  isWrapper: boolean;
  parentEntity: string;
  tableName: string;
  columns: IColumn[];
  properties: IProperty[];
  objects: IObject[];
  collections: ICollection[];
  queries: IQuery[];
  validation: IValidation;
  businessObject: IBusinessObject;
}
