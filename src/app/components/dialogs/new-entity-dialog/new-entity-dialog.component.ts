import {Component, OnInit} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';

@Component({
  selector: 'app-new-entity-dialog',
  templateUrl: './new-entity-dialog.component.html',
  styleUrls: ['./new-entity-dialog.component.css']
})
export class NewEntityDialogComponent implements OnInit {
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
  createJavaClass = false;
  isNextDisabled = false;
  isPreviousDisabled = true;
  isFinishDisabled = true;
  STEP_ENTITY_INFO = 0;
  STEP_COLUMN_INFO = 1;
  currentStep = 0;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
  }

  ngOnInit() {
  }

  OpenNextStep() {
    this.entity.columns = [
      {name: 'person_id', dataType: 'String', objectField: 'istrPersonID', maxLength: '4', canBeNull: true},
      {name: 'first_name', dataType: 'String', objectField: 'istrfirstname', maxLength: '4', canBeNull: true},
    ];
    this.isNextDisabled = true;
    this.isPreviousDisabled = false;
    this.isFinishDisabled = false;
    this.currentStep = this.STEP_COLUMN_INFO;
  }

  OpenPreviousStep() {
    this.isNextDisabled = false;
    this.isPreviousDisabled = true;
    this.isFinishDisabled = true;
    this.currentStep = this.STEP_ENTITY_INFO;
  }

  OnWizFinish() {

  }


}
