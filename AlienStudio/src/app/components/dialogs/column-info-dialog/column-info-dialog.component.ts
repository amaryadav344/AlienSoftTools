import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IColumn} from '../../../models/Enitity/IColumn';
import {R} from '../../../common/R';

@Component({
  selector: 'app-entity-info-dialog',
  templateUrl: './column-info-dialog.component.html',
  styleUrls: ['./column-info-dialog.component.css']
})
export class ColumnInfoDialogComponent implements OnInit {
  column: IColumn = R.Initializer.getColumn();
  public DataTypes: string[] = R.DataTypes;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    Object.assign(this.column, config.data.column);
  }

  ngOnInit() {
  }

  saveColumn() {
    this.ref.close();
  }


}
