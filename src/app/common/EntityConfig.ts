import {R} from './R';
import {NameCode} from './NameCode';
import {IEntity} from '../models/IEntity';
import {IQuery} from '../models/IQuery';

export class EntityConfig {
  public mEntity: IEntity;
  public QueryTypes: NameCode[] = R.QueryTypes;
  public DataTypes: NameCode[] = R.DataTypes;
  public CollectionTypes: NameCode[] = R.CollectionTypes;
  public Fields: string[] = ['istrPersonId', 'istrPersonName', 'iintEmailId', 'iintPhoneNumber'];
  CurrentQuery: IQuery = {
    expression: '',
    label: '',
    value: 1,
    parameters: [],
    customMaps: [],
  };
  mFieldSuggestions: string[];
  selection: any;
  Types: string[] = ['ibusPerson', 'ibusAccount', 'ibusCredits', 'ibusProfile'];
  Lists: string[] = ['iclbPerson', 'iclbAccount', 'iclbCredits', 'iclbProfile'];

}
