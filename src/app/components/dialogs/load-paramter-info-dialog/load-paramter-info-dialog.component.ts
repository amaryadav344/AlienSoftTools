import {Component, OnInit} from '@angular/core';
import {ILoadParameter} from '../../../models/ILoadParameter';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';

@Component({
  selector: 'app-load-paramter-info-dialog',
  templateUrl: './load-paramter-info-dialog.component.html',
  styleUrls: ['./load-paramter-info-dialog.component.css'],
})
export class LoadParamterInfoDialogComponent implements OnInit {

  loadParameters: ILoadParameter[] = [];

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    Object.assign(this.loadParameters, config.data.loadParameters);
  }

  ngOnInit() {
  }

  saveLoadParameter() {
    this.ref.close(this.loadParameters);
  }
}
