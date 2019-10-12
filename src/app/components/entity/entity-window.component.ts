import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {EntityConfig} from '../../common/EntityConfig';
import {IParameter} from '../../models/IParameter';
import {DialogService, DynamicDialogConfig, MenuItem} from 'primeng/api';
import {ColumnInfoDialogComponent} from '../dialogs/column-info-dialog/column-info-dialog.component';
import {ObjectInfoDialogComponent} from '../dialogs/object-info-dialog/object-info-dialog.component';
import {CollectionInfoDialogComponent} from '../dialogs/collection-info-dialog/collection-info-dialog.component';
import {IObject} from '../../models/IObject';
import {R} from '../../common/R';
import {ICollection} from '../../models/ICollection';
import {QueryInfoDialogComponent} from '../dialogs/query-info-diaglog/query-info-dialog.component';
import {IQuery} from '../../models/IQuery';
import {IColumn} from '../../models/IColumn';
import {Table} from 'primeng/table';
import {EntityService} from '../../services/entity-service/entity.service';

@Component({
  selector: 'app-entity-window',
  templateUrl: './entity-window.component.html',
  styleUrls: ['./entity-window.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [DialogService, EntityService],
})
export class EntityWindowComponent implements OnInit {
  mEntityConfig: EntityConfig = new EntityConfig();
  @ViewChild('tableObjects', {static: false})
  pTableRefObjects: Table;
  isTextView = false;
  xml = '';
  option = {
    fontSize: '15pt'
  };


  constructor(public dialogService: DialogService, public entityService: EntityService) {
  }

  ngOnInit() {
    this.getEntity();
  }

  getXML() {
    this.isTextView = !this.isTextView;
    this.entityService.getXMLFromJS(this.mEntityConfig.mEntity).subscribe(
      note => {
        this.xml = note;
      },
      error => {
        console.log(error);
      });
  }

  getEntity() {
    this.mEntityConfig.mEntity = {
      name: 'entPerson',
      parentEntity: 'entBase',
      columns: [],
      objects: [],
      collections: [],
      queries: [],
      validation: {
        rules: [{
          expression: 'function x() {\nconsole.log("Hello world!");\n',
          value: 'Is Salery available',
          label: 'Is Salery available',
          message: {
            message: 'Salery is not available',
            messageId: 2512,
            parameters: [{label: '{0}', objectField: 'istrPersonName'}],
          },
          groups: [{label: 'MSR Validation', value: 'MSR Validation'}, {
            label: 'MSS Validation',
            value: 'MSS Validation'
          }],
        },
          {
            expression: '{\nconsole.log("Hello world!");\n',
            value: 'Is MSR Review',
            label: 'Is MSR Review',
            message: {
              message: 'Not MSR Review',
              messageId: 2513,
              parameters: [{label: '{1}', objectField: 'istrPersonName'}],
            },
            groups: [{
              label: 'MSS Validation',
              value: 'MSS Validation'
            }],
          }],
        groups: [{label: 'MSR Validation', value: 'MSR Validation'}, {label: 'MSS Validation', value: 'MSS Validation'}]
      },
      businessObject: {
        customMethods: [{
          name: 'LoadFormData',
          mode: {name: 'All', code: 'ALL'},
          loadPrimaryObject: true,
          loadMapping: [{
            name: 'GetPerson',
            loadType: {name: 'Query', code: 'QER'},
            loadParameters: [{name: '@PERSON_ID', entityField: 'iintPersonID'}, {
              name: '@PERSON_ID',
              entityField: 'iintPersonID'
            }, {name: '@PERSON_ID', entityField: 'iintPersonID'},
              {name: '@PERSON_ID', entityField: 'iintPersonID'}]
          }]
        }],
        objectMethods: [{
          name: 'GetAllUsers',
          returnType: 'cdoUsers',
          objectParameters: [{name: 'aintPersonID', dataType: 'string'}]
        }]
      }
    };
    this.mEntityConfig.mEntity.columns = [
      {name: 'person_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrPersonID'},
      {name: 'first_name', dataType: {name: 'String', code: 'STR'}, objectField: 'istrfirstname'},
      {name: 'middle_name', dataType: {name: 'String', code: 'STR'}, objectField: 'istrMiddleName'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
      {name: 'email_id', dataType: {name: 'String', code: 'STR'}, objectField: 'istrEmailId'},
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
        queryType: {name: '', code: ''},
        value: 'GetPersonByPersonId',
        parameters: [
          {name: '@PESON_ID', dataType: {name: '', code: ''}}
        ],
        customMaps: [
          {column: 'EMAIL_ID', objectField: 'istrEmailID'}
        ],
      },
      {
        expression: 'ibuspersion.icdoPerson.person_id > 0',
        label: 'GetPersonByOrgId',
        value: 'GetPersonByOrgId',
        queryType: {name: '', code: ''},
        parameters: [
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
          {name: '@ORG_ID', dataType: {name: '', code: ''}},
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


