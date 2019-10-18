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

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
  }

  ngOnInit() {
  }

}
