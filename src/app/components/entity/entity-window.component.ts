import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DialogService} from 'primeng/api';
import {EntityService} from '../../services/entity-service/entity.service';
import {TabChangeServiceService} from '../../services/tab-change/tab-change-service.service';
import {IFile} from '../../models/IFile';
import {IEntity} from '../../models/IEntity';


@Component({
  selector: 'app-entity-window',
  templateUrl: './entity-window.component.html',
  styleUrls: ['./entity-window.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [DialogService, EntityService],
})
export class EntityWindowComponent implements OnInit {
  // TODO:Remove this
  entity: IEntity = {
    name: '',
    parentEntity: '',
    tableName: '',
    modelName: '',
    validation: {
      rules: [],
      groupRules: [],
      hardErrors: [],
      softErrors: [],
      initialLoad: [],
      updateRules: [],
      deleteRules: []
    }, queries: [], collections: [], objects: [], columns: [], businessObject: {customMethods: [], objectMethods: []}
  };
  file: IFile;
  isTextView = false;
  xml = '';


  constructor(public dialogService: DialogService, public entityService: EntityService,
              public tabChangeService: TabChangeServiceService) {
  }

  ngOnInit() {
    this.entityService.getFile(this.file).subscribe(
      (entity) => {
        this.entity = entity;
        this.checkForUndefined(this.entity);
      }, (error) => {
        console.log(error);
      });
  }


  getXML() {
    this.entityService.getXMLFromJS(this.entity).subscribe(
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
        this.entity = entity;
        this.checkForUndefined(this.entity);
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

  checkForUndefined(entity: IEntity) {
    if (!this.entity.columns) {
      this.entity.columns = [];
    }
    if (!this.entity.objects) {
      this.entity.objects = [];
    }
    if (!this.entity.collections) {
      this.entity.collections = [];
    }
    if (!this.entity.validation) {
      this.entity.validation = {
        rules: [],
        groupRules: [],
        deleteRules: [],
        hardErrors: [],
        softErrors: [],
        initialLoad: [],
        updateRules: []
      };
    }
    if (!this.entity.validation.rules) {
      this.entity.validation.rules = [];
    }
    if (!this.entity.validation.deleteRules) {
      this.entity.validation.deleteRules = [];
    }
    if (!this.entity.validation.updateRules) {
      this.entity.validation.updateRules = [];
    }
    if (!this.entity.validation.initialLoad) {
      this.entity.validation.initialLoad = [];
    }
    if (!this.entity.validation.softErrors) {
      this.entity.validation.softErrors = [];
    }
    if (!this.entity.validation.hardErrors) {
      this.entity.validation.hardErrors = [];
    }
    if (!this.entity.validation.deleteRules) {
      this.entity.validation.deleteRules = [];
    }
    if (!this.entity.validation.groupRules) {
      this.entity.validation.groupRules = [];
    }
    if (!this.entity.queries) {
      this.entity.queries = [];
    }
    if (!this.entity.businessObject) {
      this.entity.businessObject = {customMethods: [], objectMethods: []};
    }
    if (!this.entity.businessObject.objectMethods) {
      this.entity.businessObject.objectMethods = [];
    }
    if (!this.entity.businessObject.customMethods) {
      this.entity.businessObject.customMethods = [];
    }
  }

  getEntity() {
    this.entity = {
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
    this.entity.columns = [
      {name: 'person_id', dataType: 'String', objectField: 'istrPersonID', maxLength: '4', canBeNull: true},
      {name: 'first_name', dataType: 'String', objectField: 'istrfirstname', maxLength: '4', canBeNull: true},
    ];
    this.entity.objects = [
      {name: 'objPerson', entity: 'entPerson', objectField: 'ibusPerson'},
      {name: 'objAccount', entity: 'entAccount', objectField: 'ibusAcoount'},
    ];

    this.entity.collections = [
      {name: 'lstPerson', entity: 'entPerson', objectField: 'iclbPerson', dataType: 'List'},
      {name: 'lstAccount', entity: 'entAccount', objectField: 'iclbAccount', dataType: 'Queue'},
    ];
    this.entity.queries = [
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


