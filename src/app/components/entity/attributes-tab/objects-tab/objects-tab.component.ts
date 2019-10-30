import {Component, Input, OnInit} from '@angular/core';
import {IObject} from '../../../../models/IObject';
import {ObjectInfoDialogComponent} from '../../../dialogs/object-info-dialog/object-info-dialog.component';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {R} from '../../../../common/R';

@Component({
  selector: 'app-objects-tab',
  templateUrl: './objects-tab.component.html',
  styleUrls: ['./objects-tab.component.css'],
  providers: [DialogService],
})
export class ObjectsTabComponent implements OnInit {
  @Input() objects: IObject[];
  selection: IObject;

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  onObjectSelect(Mode: number) {
    const ref = this.dialogService.open(ObjectInfoDialogComponent, {
      data: {
        object: this.selection,
        mode: Mode
      },
      header: 'Object Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((object: IObject) => {
      if (object) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, object);
        } else {
          this.objects.push(object);
        }
      }
    });
  }

  deleteObject(object: IObject) {
    const index = this.objects.indexOf(object);
    this.objects.splice(index, 1);
  }

  viewObject(object: IObject) {
    this.selection = object;
    this.onObjectSelect(1);
  }

}
