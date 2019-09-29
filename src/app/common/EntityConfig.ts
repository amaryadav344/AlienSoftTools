import {R} from './R';
import {NameCode} from './NameCode';
import {IEntity} from '../models/IEntity';
import {IQuery} from '../models/IQuery';
import {MenuItem} from "primeng/api";

export class EntityConfig {
  public mEntity: IEntity;
  public QueryTypes: NameCode[] = R.QueryTypes;
  public DataTypes: NameCode[] = R.DataTypes;
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
  Fields: string[] = ['istrPersonId', 'istrPersonName', 'iintEmailId', 'iintPhoneNumber'];
  QueryOptions: MenuItem[] = [
    {
      label: 'Next',
      icon: 'pi pi-fw pi-chevron-right'
    },
    {
      label: 'Prev',
      icon: 'pi pi-fw pi-chevron-left'
    }
  ];

}
