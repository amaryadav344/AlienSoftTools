import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {ILoadMapping} from "../../../models/Enitity/ILoadMapping";

@Component({
  selector: 'app-load-paramter-info-dialog',
  templateUrl: './load-paramter-info-dialog.component.html',
  styleUrls: ['./load-paramter-info-dialog.component.css'],
})
export class LoadParamterInfoDialogComponent implements OnInit {

  loadMappings: ILoadMapping[] = [];

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    Object.assign(this.loadMappings, config.data.loadMapping);
  }

  ngOnInit() {
  }

  saveLoadParameter() {
    this.ref.close(this.loadMappings);
  }
}
