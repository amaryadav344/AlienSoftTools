import {Component, OnInit} from '@angular/core';

import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {NewEntityDialogComponent} from '../dialogs/new-entity-dialog/new-entity-dialog.component';
import {EntityService} from '../../services/entity-service/entity.service';
import {WindowService} from "../../services/window/window.service";

@Component({
  selector: 'app-home-window',
  templateUrl: './home-window.component.html',
  styleUrls: ['./home-window.component.css'],
  providers: [DialogService, EntityService],
})
export class HomeWindowComponent implements OnInit {

  constructor(public dialogService: DialogService, public windowService: WindowService) {

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

    ref.onClose.subscribe((path: string) => {
      if (path) {
        this.windowService.Openwindow({path: path, type: 0});
      }
    });
  }

}
