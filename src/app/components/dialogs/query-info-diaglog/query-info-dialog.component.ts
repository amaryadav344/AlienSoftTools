import {Component, OnInit} from '@angular/core';
import {IQuery} from '../../../models/IQuery';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';

@Component({
  selector: 'app-query-info-dialog',
  templateUrl: './query-info-dialog.component.html',
  styleUrls: ['./query-info-dialog.component.css']
})
export class QueryInfoDialogComponent implements OnInit {
  query: IQuery = R.Initializer.getQuery();
  mode: number;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.query, config.data.query);
    }
  }

  ngOnInit() {
  }

  saveColumn() {
    this.ref.close(this.query);
  }

}
