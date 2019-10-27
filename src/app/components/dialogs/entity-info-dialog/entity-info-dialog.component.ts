import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IEntity} from '../../../models/IEntity';

@Component({
  selector: 'app-entity-info-dialog',
  templateUrl: './entity-info-dialog.component.html',
  styleUrls: ['./entity-info-dialog.component.css']
})
export class EntityInfoDialogComponent implements OnInit {
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

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    Object.assign(this.entity, config.data.entity);
  }

  ngOnInit() {
  }

  saveEntity() {
    this.ref.close(this.entity);
  }

}
