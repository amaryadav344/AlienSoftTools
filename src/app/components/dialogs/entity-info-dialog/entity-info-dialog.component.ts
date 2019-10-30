import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IEntity} from '../../../models/IEntity';
import {R} from '../../../common/R';
import {WindowService} from '../../../services/window/window.service';
import {IFile} from '../../../models/IFile';

@Component({
  selector: 'app-entity-info-dialog',
  templateUrl: './entity-info-dialog.component.html',
  styleUrls: ['./entity-info-dialog.component.css']
})
export class EntityInfoDialogComponent implements OnInit {
  entity: IEntity = R.Initializer.getEntity();
  file: IFile = R.Initializer.getFile();

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public windowService: WindowService) {
    Object.assign(this.entity, config.data.entity);
  }

  ngOnInit() {
    this.file = this.windowService.windowStore.getCurrentWindow().data;
  }

  saveEntity() {
    this.ref.close(this.entity);
  }

}
