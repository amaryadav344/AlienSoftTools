import {Component, OnInit} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {EntityService} from "../../../services/entity-service/entity.service";

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
  folders: string[] = [];
  tables: string[] = [];
  path: string;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public entityService: EntityService) {
  }

  ngOnInit() {
  }

  OpenNextStep() {
    this.entityService.getColumns(this.entity.tableName).subscribe(
      (columns) => {
        this.entity.columns = columns;
      },
      (err) => {
        console.log(err);
      }
    );
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
    this.entityService.createNewXml(this.entity, this.path).subscribe(
      (res) => {
        this.ref.close(this.path + '/' + this.entity.name + '.ent.xml');
      }, (err) => {
        console.log(err);
      }
    );
  }

  getMatchingFolders(query) {
    this.entityService.getMatchingFolders(query).subscribe(
      (folders) => {
        this.folders = folders;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getMatchingTableNames(query) {
    this.entityService.getMatchingTables(query).subscribe(
      (tables) => {
        this.tables = tables;
      },
      (err) => {
        console.log(err);
      }
    );
  }


}
