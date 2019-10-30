import {Component, OnInit} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
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
    this.httpClientService.getColumns(this.entity.tableName).subscribe(
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
    this.httpClientService.createNewXml(this.entity, this.path, this.createJavaClass).subscribe(
      (res) => {
        this.ref.close({path: this.path + '/' + this.entity.name + '.ent.xml', name: this.entity.name, type: 0});
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


}
