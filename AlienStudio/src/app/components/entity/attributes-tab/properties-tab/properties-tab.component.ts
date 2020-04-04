import {Component, Input, OnInit} from '@angular/core';
import {IProperty} from '../../../../models/Enitity/IProperty';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {R} from '../../../../common/R';
import {PropertyInfoDialogComponent} from '../../../dialogs/property-info-dialog/property-info-dialog.component';

@Component({
  selector: 'app-properties-tab',
  templateUrl: './properties-tab.component.html',
  styleUrls: ['./properties-tab.component.css']
})
export class PropertiesTabComponent implements OnInit {
  @Input() properties: IProperty[];
  selection: IProperty;

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  onPropertyselect(Mode: number) {
    const ref = this.dialogService.open(PropertyInfoDialogComponent, {
      data: {
        property: this.selection,
        mode: Mode
      },
      header: 'Object Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((property: IProperty) => {
      if (property) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, property);
        } else {
          this.properties.push(property);
        }
      }
    });
  }

  deleteObject(property: IProperty) {
    const index = this.properties.indexOf(property);
    this.properties.splice(index, 1);
  }

  viewObject(property: IProperty) {
    this.selection = property;
    this.onPropertyselect(1);
  }
}
