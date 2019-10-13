import {R} from './R';
import {IEntity} from '../models/IEntity';
import {IQuery} from '../models/IQuery';

export class EntityConfig {
  public mEntity: IEntity;
  public QueryTypes: string[] = R.QueryTypes;
  public DataTypes: string[] = R.DataTypes;
  CurrentQuery: IQuery = {
    sql: '',
    name: '',
    parameters: [],
    customMaps: [],
  };
  mFieldSuggestions: string[];
  selection: any;
  Types: string[] = ['ibusPerson', 'ibusAccount', 'ibusCredits', 'ibusProfile'];
  Lists: string[] = ['iclbPerson', 'iclbAccount', 'iclbCredits', 'iclbProfile'];
  Fields: string[] = ['istrPersonId', 'istrPersonName', 'iintEmailId', 'iintPhoneNumber'];


}
