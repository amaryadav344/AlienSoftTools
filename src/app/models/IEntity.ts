import {IColumn} from './IColumn';
import {IObject} from './IObject';
import {ICollection} from './ICollection';
import {IQuery} from './IQuery';
import {IValidation} from './IValidation';
import {IBusinessObject} from './IBusinessObject';

export interface IEntity {
  name: string;
  parentEntity: string;
  tableName: string;
  modelName: string;
  columns: IColumn[];
  objects: IObject[];
  collections: ICollection[];
  queries: IQuery[];
  validation: IValidation;
  businessObject: IBusinessObject;
}
