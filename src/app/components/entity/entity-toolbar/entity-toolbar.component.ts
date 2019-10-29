import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {EntityInfoDialogComponent} from '../../dialogs/entity-info-dialog/entity-info-dialog.component';


@Component({
  selector: 'app-entity-toolbar',
  templateUrl: './entity-toolbar.component.html',
  styleUrls: ['./entity-toolbar.component.css']
})
export class EntityToolbarComponent implements OnInit {
  @Input() entity: IEntity;
  @Output() switchToXMl = new EventEmitter<boolean>();
  @Output() switchToUI = new EventEmitter<boolean>();
  @Output() closeWindow = new EventEmitter();
  @Output() saveXML = new EventEmitter();
  view = false;

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  openEntityInfo() {
    const ref = this.dialogService.open(EntityInfoDialogComponent, {
      data: {
        entity: this.entity,
      },
      header: 'Group Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((entity: IEntity) => {
      if (entity) {
        Object.assign(this.entity, entity);

      }
    });
  }

}
