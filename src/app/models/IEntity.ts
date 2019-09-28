import {IColumn} from './IColumn';
import {IObject} from './IObject';
import {ICollection} from "./ICollection";
import {IQuery} from "./IQuery";

export interface IEntity {
  columns: IColumn[];
  objects: IObject[];
  collections: ICollection[];
  queries: IQuery[];
}
