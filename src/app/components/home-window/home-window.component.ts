import {Component, OnInit} from '@angular/core';

import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {NewEntityDialogComponent} from '../dialogs/new-entity-dialog/new-entity-dialog.component';
import {EntityService} from '../../services/entity-service/entity.service';
import {WindowService} from '../../services/window/window.service';
import {WindowBase} from '../window/window-base/WindowBase';
import {IFile} from '../../models/IFile';

@Component({
  selector: 'app-home-window',
  templateUrl: './home-window.component.html',
  styleUrls: ['./home-window.component.css'],
  providers: [DialogService, EntityService],
})
export class HomeWindowComponent extends WindowBase implements OnInit {
  constructor(public dialogService: DialogService, public windowService: WindowService) {
    super();
  }

  ngOnInit() {
  }

  CreateNewEntity() {
    const ref = this.dialogService.open(NewEntityDialogComponent, {
      data: {},
      header: 'New Entity',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((file: IFile) => {
      if (file) {
        this.windowService.Openwindow(file);
        this.windowService.syncFiles(file);
      }
    });
  }

}
