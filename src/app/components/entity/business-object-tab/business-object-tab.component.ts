import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {IBusinessObject} from '../../../models/IBusinessObject';
import {ICustomMethod} from '../../../models/ICustomMethod';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {R} from '../../../common/R';
import {CustomMethodInfoDialogComponent} from '../../dialogs/custom-method-info-dialog/custom-method-info-dialog.component';
import {IObjectMethod} from '../../../models/IObjectMethod';
import {ObjectMethodInfoDialogComponent} from '../../dialogs/object-method-info-dialog/object-method-info-dialog.component';

@Component({
  selector: 'app-business-object-tab',
  templateUrl: './business-object-tab.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./business-object-tab.component.css']
})
export class BusinessObjectTabComponent implements OnInit {
  @Input() businessObject: IBusinessObject;
  selection: ICustomMethod;

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  onCustomMethodSelect(Mode) {
    const ref = this.dialogService.open(CustomMethodInfoDialogComponent, {
      data: {
        customMethod: this.selection,
        customMethods: this.businessObject.customMethods,
        mode: Mode
      },
      header: 'Object Information',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((customMethod: ICustomMethod) => {
      if (customMethod) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, customMethod);
        } else {
          this.businessObject.customMethods.push(customMethod);
          this.businessObject.customMethods = [...this.businessObject.customMethods];
        }
      }
    });
  }

  deleteCustomMethod(customMethod: ICustomMethod) {
    const index = this.businessObject.customMethods.indexOf(customMethod);
    this.businessObject.customMethods.splice(index, 1);
  }

  onObjectMethodSelect(Mode) {
    const ref = this.dialogService.open(ObjectMethodInfoDialogComponent, {
      data: {
        objectMethod: this.selection,
        objectMethods: this.businessObject.objectMethods,
        mode: Mode
      },
      header: 'Object Information',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((objectMethod: IObjectMethod) => {
      if (objectMethod) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, objectMethod);
        } else {
          this.businessObject.objectMethods.push(objectMethod);
          this.businessObject.objectMethods = [...this.businessObject.objectMethods];
        }
      }
    });
  }

  deleteObjectMethod(customMethod: ICustomMethod) {
    const index = this.businessObject.customMethods.indexOf(customMethod);
    this.businessObject.customMethods.splice(index, 1);
  }

}
