import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IForm} from '../../../models/UI/IForm';
import {FormInfoDialogComponent} from '../../dialogs/form-info-dialog/form-info-dialog.component';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {IEntity} from '../../../models/Enitity/IEntity';

@Component({
  selector: 'app-uitoolbar',
  templateUrl: './uitoolbar.component.html',
  styleUrls: ['./uitoolbar.component.css']
})
export class UIToolbarComponent implements OnInit {
  @Output() switchToXMl = new EventEmitter<boolean>();
  @Output() switchToUI = new EventEmitter<boolean>();
  @Output() closeWindow = new EventEmitter();
  @Output() saveXML = new EventEmitter();
  @Input() form: IForm;
  @Input() entity: IEntity;
  view = false;


  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  openFormInfo() {
    const ref = this.dialogService.open(FormInfoDialogComponent, {
      data: {
        form: this.form,
        entity: this.entity,
      },
      header: 'Form Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((form: IForm) => {
      if (form) {
        Object.assign(this.form, form);
      }
    });
  }

}
