import {Component, OnInit} from '@angular/core';

import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {NewEntityDialogComponent} from '../dialogs/new-entity-dialog/new-entity-dialog.component';
import {IEntity} from '../../models/IEntity';
import {EntityService} from '../../services/entity-service/entity.service';

@Component({
  selector: 'app-home-window',
  templateUrl: './home-window.component.html',
  styleUrls: ['./home-window.component.css'],
  providers: [DialogService, EntityService],
})
export class HomeWindowComponent implements OnInit {

  constructor(public dialogService: DialogService) {

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

    ref.onClose.subscribe((entity: IEntity) => {
      if (entity) {
      }
    });
  }

}
