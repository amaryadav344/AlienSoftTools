import {Component, OnInit} from '@angular/core';
import {R} from '../../../common/R';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IObject} from '../../../models/IObject';

@Component({
  selector: 'app-object-info-dialog',
  templateUrl: './object-info-dialog.component.html',
  styleUrls: ['./object-info-dialog.component.css']
})
export class ObjectInfoDialogComponent implements OnInit {

  object: IObject;
  Types: string[];
  mFieldSuggestions: string[];
  mode: number;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      this.object = {
        name: config.data.object.name,
        entity: config.data.object.entity,
        objectField: config.data.object.objectField
      };
    } else {
      this.object = {
        name: '',
        entity: '',
        objectField: ''
      };
    }
    this.Types = config.data.types;
  }

  ngOnInit() {
  }

  saveColumn() {
    this.ref.close(this.object);
  }

  filterCountrySingle(event) {
    this.mFieldSuggestions = [];
    for (const field of this.Types) {
      if (field.toLowerCase().indexOf(event.query.toLowerCase()) >= 0) {
        this.mFieldSuggestions.push(field);
      }
    }
  }


}
