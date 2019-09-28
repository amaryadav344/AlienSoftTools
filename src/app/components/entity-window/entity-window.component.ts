import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EntityConfig} from '../../common/EntityConfig';
import {IParameter} from '../../models/IParameter';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {ColumnInfoDialogComponent} from '../dialogs/column-info-dialog/column-info-dialog.component';
import {ObjectInfoDialogComponent} from '../dialogs/object-info-dialog/object-info-dialog.component';
import {CollectionInfoDialogComponent} from '../dialogs/collection-info-dialog/collection-info-dialog.component';
import {IObject} from "../../models/IObject";
import {R} from "../../common/R";
import {ICollection} from "../../models/ICollection";

@Component({
  selector: 'app-entity-window',
  templateUrl: './entity-window.component.html',
  styleUrls: ['./entity-window.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [DialogService],
})
export class EntityWindowComponent implements OnInit {
  mEntityConfig: EntityConfig = new EntityConfig();

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
    this.getEntity();
  }

  onColumnSelect() {
    const ref = this.dialogService.open(ColumnInfoDialogComponent, {
        data: {
          column: this.mEntityConfig.selection,
          fields: this.mEntityConfig.Fields,
        },
        header: 'Column Information',
        width:
          '40%',
        contentStyle:
          {
            'max-height':
              '700px', overflow:
            'auto'
          }
      }as DynamicDialogConfig
      )
    ;
  }

  onObjectSelect(Mode: number) {
    const ref = this.dialogService.open(ObjectInfoDialogComponent, {
      data: {
        object: this.mEntityConfig.selection,
        types: this.mEntityConfig.Types,
        mode: Mode
      },
      header: 'Object Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((object: IObject) => {
      if (object) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.mEntityConfig.selection, object);
        } else {
          this.mEntityConfig.mEntity.objects.push(object);
        }
      }
    });
  }

  onCollectionSelect(Mode: number) {
    const ref = this.dialogService.open(CollectionInfoDialogComponent, {
      data: {
        collection: this.mEntityConfig.selection,
        lists: this.mEntityConfig.Lists,
        mode: Mode,
      },
      header: 'Collection Information',
      width:
        '40%',
      contentStyle:
        {
          'max-height':
            '700px', overflow:
          'auto'
        }
    }as DynamicDialogConfig);
    ref.onClose.subscribe((collection: ICollection) => {
      if (collection) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.mEntityConfig.selection, collection);
        } else {
          this.mEntityConfig.mEntity.collections.push(collection);
        }
      }
    });
  }

  refreshParameters() {
    const newParameters: IParameter[] = [];
    const re = /@\w*/g;
    const Parameters: string[] = re.exec(this.mEntityConfig.CurrentQuery.expression);
    if (Parameters) {
      for (const Parameter of Parameters) {
        for (const PreviousParam of this.mEntityConfig.CurrentQuery.parameters) {
          if (PreviousParam.name === Parameter) {
            newParameters.push({name: PreviousParam.name, dataType: ''});
          } else {
            newParameters.push(PreviousParam);
          }
        }
      }
    }
    this.mEntityConfig.CurrentQuery.parameters = newParameters;
  }


  filterCountrySingle(event) {
    this.mEntityConfig.mFieldSuggestions = [];
    for (const field of this.mEntityConfig.Fields) {
      if (field.toLowerCase().indexOf(event.query.toLowerCase()) >= 0) {
        this.mEntityConfig.mFieldSuggestions.push(field);
      }
    }
  }


  getEntity() {
    this.mEntityConfig.mEntity = {columns: [], objects: [], collections: [], queries: []};
    this.mEntityConfig.mEntity.columns = [
      {name: 'person_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrPersonID'},
      {name: 'first_name', dataType: {name: 'String', code: 'STR'}, objectField: 'istrfirstname'},
      {name: 'middle_name', dataType: {name: 'String', code: 'STR'}, objectField: 'istrMiddleName'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'phone_number', dataType: {name: 'String', code: 'STR'}, objectField: 'iintPhoneNumber'},
    ];
    this.mEntityConfig.mEntity.objects = [
      {name: 'objPerson', entity: 'entPerson', objectField: 'ibusPerson'},
      {name: 'objAccount', entity: 'entAccount', objectField: 'ibusAcoount'},
    ];

    this.mEntityConfig.mEntity.collections = [
      {name: 'lstPerson', entity: 'entPerson', objectField: 'iclbPerson', dataType: {name: 'List', code: 'LST'}},
      {name: 'lstAccount', entity: 'entAccount', objectField: 'iclbAccount', dataType: {name: 'Queue', code: 'QUE'}},
    ];
    this.mEntityConfig.mEntity.queries = [
      {
        expression: 'ibuspersion.icdoPerson.person_id > 0',
        label: 'GetPersonByPersonId',
        value: 1,
        parameters: [
          {name: '@PESON_ID', dataType: 'integer'}
        ],
        customMaps: [
          {column: 'EMAIL_ID', objectField: 'istrEmailID'}
        ],
      },
      {
        expression: 'ibuspersion.icdoPerson.person_id > 0',
        label: 'GetPersonByOrgId',
        value: 2,
        parameters: [
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
          {name: '@ORG_ID', dataType: 'integer'},
        ],
        customMaps: [
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
          {column: 'PERSON_NAME', objectField: 'istrPersonName'},
        ],
      },
    ];
  }
}


