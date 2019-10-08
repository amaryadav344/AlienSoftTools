import {Component, OnInit} from '@angular/core';
import {IObjectMethod} from '../../../models/IObjectMethod';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';

@Component({
  selector: 'app-object-method-info-dialog',
  templateUrl: './object-method-info-dialog.component.html',
  styleUrls: ['./object-method-info-dialog.component.css']
})
export class ObjectMethodInfoDialogComponent implements OnInit {

  objectMethod: IObjectMethod = {
    name: '',
    returnType: '',
    objectParameters: []
  };

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.objectMethod, config.data.objectMethod);
    }
  }

  ngOnInit() {
  }

  saveObjectMethod() {
    this.ref.close(this.objectMethod);
  }

}
