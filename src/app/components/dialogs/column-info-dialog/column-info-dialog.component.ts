import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IColumn} from '../../../models/IColumn';
import {NameCode} from '../../../common/NameCode';
import {R} from '../../../common/R';

@Component({
  selector: 'app-entity-info-dialog',
  templateUrl: './column-info-dialog.component.html',
  styleUrls: ['./column-info-dialog.component.css']
})
export class ColumnInfoDialogComponent implements OnInit {
  column: IColumn;
  orginalColumn: IColumn;
  Fields: string[];
  mFieldSuggestions: string[];
  public DataTypes: NameCode[] = R.DataTypes;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    this.column = {
      name: config.data.column.name,
      dataType: config.data.column.dataType,
      objectField: config.data.column.objectField
    };
    this.Fields = config.data.fields;
    this.orginalColumn = config.data.column;
  }

  ngOnInit() {
  }

  saveColumn() {
    // Object.assign(this.orginalColumn, this.column);
    this.ref.close();
  }

  filterCountrySingle(event) {
    this.mFieldSuggestions = [];
    for (const field of this.Fields) {
      if (field.toLowerCase().indexOf(event.query.toLowerCase()) >= 0) {
        this.mFieldSuggestions.push(field);
      }
    }
  }

}
