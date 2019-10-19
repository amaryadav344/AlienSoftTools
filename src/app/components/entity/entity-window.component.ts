import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {EntityConfig} from '../../common/EntityConfig';
import {DialogService} from 'primeng/api';
import {Table} from 'primeng/table';
import {EntityService} from '../../services/entity-service/entity.service';
import {TabChangeServiceService} from '../../services/tab-change/tab-change-service.service';


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


  constructor(public dialogService: DialogService, public entityService: EntityService,
              public tabChangeService: TabChangeServiceService) {
  }

  ngOnInit() {
    this.getEntity();
  }

  getXML() {
    this.entityService.getXMLFromJS(this.mEntityConfig.mEntity).subscribe(
      xml => {
        this.xml = xml;
        this.isTextView = !this.isTextView;
      },
      error => {
        console.log(error);
      });
  }

  getJS() {
    this.entityService.getJSFromXML(this.xml).subscribe(
      entity => {
        this.mEntityConfig.mEntity = entity;
        this.isTextView = !this.isTextView;
      },
      error => {
        console.log(error);
      });
  }

  tabChanged(e) {
    const index = e.index;
    this.tabChangeService.tabChanged(index);
  }


  getEntity() {
    this.mEntityConfig.mEntity = {
      name: 'entPerson',
      parentEntity: 'entBase',
      tableName: '',
      modelName: '',
      columns: [],
      objects: [],
      collections: [],
      queries: [],
      validation: {
        rules: [{
          expression: 'function x() {\nconsole.log("Hello world!");\n',
          name: 'Is Salery available',
          message: {
            message: 'Salery is not available',
            messageId: 2512,
            parameters: [{label: '{0}', objectField: 'istrPersonName'}],
          },
        },
          {
            expression: '{\nconsole.log("Hello world!");\n',
            name: 'Is MSR Review',
            message: {
              message: 'Not MSR Review',
              messageId: 2513,
              parameters: [{label: '{1}', objectField: 'istrPersonName'}],
            }
          }],
        initialLoad: [{name: 'Is Salery available'}],
        hardErrors: [{name: 'Is Salery available'}],
        softErrors: [{name: 'Is Salery available'}, {name: 'Is MSR Review'}],
        deleteRules: [{name: 'Is Salery available'}, {name: 'Is MSR Review'}],
        updateRules: [{name: 'Is Salery available'}, {name: 'Is MSR Review'}],
        groupRules: [{
          name: 'Review', rules: [{name: 'Is Salery available'}]
        }],
      },
      businessObject: {
        customMethods: [{
          name: 'LoadFormData',
          mode: 'All',
          loadPrimaryObject: true,
          loadMapping: [{
            name: 'GetPerson',
            loadType: 'Query',
            loadParameters: [{name: '@PERSON_ID', entityField: 'iintPersonID'}, {
              name: '@PERSON_ID',
              entityField: 'iintPersonID'
            }, {name: '@PERSON_ID', entityField: 'iintPersonID'},
              {name: '@PERSON_ID', entityField: 'iintPersonID'}]
          }]
        }],
        objectMethods:
          [{
            name: 'GetAllUsers',
            returnType: 'cdoUsers',
            objectParameters: [{name: 'aintPersonID', dataType: 'string'}]
          }]
      }
    }
    ;
    this.mEntityConfig.mEntity.columns = [
      {name: 'person_id', dataType: 'String', objectField: 'istrPersonID'},
      {name: 'first_name', dataType: 'String', objectField: 'istrfirstname'},
      {name: 'middle_name', dataType: 'String', objectField: 'istrMiddleName'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'email_id', dataType: 'String', objectField: 'istrEmailId'},
      {name: 'phone_number', dataType: 'String', objectField: 'iintPhoneNumber'},
    ];
    this.mEntityConfig.mEntity.objects = [
      {name: 'objPerson', entity: 'entPerson', objectField: 'ibusPerson'},
      {name: 'objAccount', entity: 'entAccount', objectField: 'ibusAcoount'},
    ];

    this.mEntityConfig.mEntity.collections = [
      {name: 'lstPerson', entity: 'entPerson', objectField: 'iclbPerson', dataType: 'List'},
      {name: 'lstAccount', entity: 'entAccount', objectField: 'iclbAccount', dataType: 'Queue'},
    ];
    this.mEntityConfig.mEntity.queries = [
      {
        sql: 'ibuspersi',
        name: 'GetPersonByPersonId',
        queryType: 'Scalar Query',
        parameters: [
          {name: '@PESON_ID', dataType: 'String'}
        ],
        customMaps: [
          {column: 'EMAIL_ID', objectField: 'istrEmailID'}
        ],
      },
      {
        sql: 'ibuspersion.icdoPerson.person_id > 0',
        name: 'GetPersonByOrgId',
        queryType: 'Sub Query',
        parameters: [
          {name: '@ORG_ID', dataType: 'String'},
          {name: '@ORG_ID', dataType: 'String'},
          {name: '@ORG_ID', dataType: 'String'},
          {name: '@ORG_ID', dataType: 'String'},
          {name: '@ORG_ID', dataType: 'String'},
          {name: '@ORG_ID', dataType: 'String'},
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


