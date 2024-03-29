import {Component, OnInit} from '@angular/core';
import {IForm} from '../../../models/UI/IForm';
import {R} from '../../../common/R';
import {IFile} from '../../../models/Enitity/IFile';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IEntity} from '../../../models/Enitity/IEntity';

@Component({
  selector: 'app-form-info-dialog',
  templateUrl: './form-info-dialog.component.html',
  styleUrls: ['./form-info-dialog.component.css']
})
export class FormInfoDialogComponent implements OnInit {
  form: IForm = R.Initializer.getForm();
  entity: IEntity = R.Initializer.getEntity();
  file: IFile = R.Initializer.getFile();

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public windowService: WindowService, public httpClientService: HttpClientService) {
    Object.assign(this.form, config.data.form);
    Object.assign(this.entity, config.data.entity);
  }

  ngOnInit() {
    this.file = this.windowService.windowStore.getCurrentWindow().data;
  }

  closeDialog() {
    this.ref.close(this.form);
  }
}
