import {Component, OnInit} from '@angular/core';
import {IEntity} from '../../../models/Enitity/IEntity';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';

@Component({
  selector: 'app-new-entity-dialog',
  templateUrl: './new-entity-dialog.component.html',
  styleUrls: ['./new-entity-dialog.component.css']
})
export class NewEntityDialogComponent implements OnInit {
  entity: IEntity = R.Initializer.getEntity();
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

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public httpClientService: HttpClientService) {
  }

  ngOnInit() {
  }

  OpenNextStep() {
    if (this.entity.tableName === null || this.entity.tableName === '') {
      this.entity.isWrapper = true;
    } else {
    }
    this.httpClientService.getColumns(this.entity.tableName).subscribe(
      (attributes) => {
        this.entity.attributes = attributes;
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
    this.httpClientService.createNewXml(this.entity, this.path, this.createJavaClass).subscribe(
      (res) => {
        this.ref.close({
          path: res,
          name: this.entity.name + '.ent.xml',
          type: 0
        });
      }, (err) => {
        console.log(err);
      }
    );
  }

  getMatchingFolders(query) {
    this.httpClientService.getMatchingFolders(query).subscribe(
      (folders) => {
        this.folders = folders;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getMatchingTableNames(query) {
    this.httpClientService.getMatchingTables(query).subscribe(
      (tables) => {
        this.tables = tables;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  TableNameChnage() {
    const name = this.toPascalCase(this.entity.tableName).replace('Ast', '');
    this.entity.name = name;
    this.entity.serviceName = name + 'Service';
    this.entity.businessObject = name;
  }


  toPascalCase(str) {
    const arr = str.split(/\s|_/);
    for (let i = 0, l = arr.length; i < l; i++) {
      arr[i] = arr[i].substr(0, 1).toUpperCase() +
        (arr[i].length > 1 ? arr[i].substr(1).toLowerCase() : '');
    }
    return arr.join('');
  }


}
