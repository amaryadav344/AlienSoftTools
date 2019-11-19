import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {ICustomMethod} from '../../../models/Enitity/ICustomMethod';
import {ILoadMapping} from '../../../models/Enitity/ILoadMapping';
import {ILoadParameter} from '../../../models/Enitity/ILoadParameter';
import {LoadParamterInfoDialogComponent} from '../load-paramter-info-dialog/load-paramter-info-dialog.component';
import {WindowService} from '../../../services/window/window.service';
import {IEntity} from '../../../models/Enitity/IEntity';
import {EntityWindowComponent} from '../../entity/entity-window.component';

@Component({
  selector: 'app-custom-method-info-dialog',
  templateUrl: './custom-method-info-dialog.component.html',
  styleUrls: ['./custom-method-info-dialog.component.css'],
  providers: [DialogService],

})
export class CustomMethodInfoDialogComponent implements OnInit {
  mFieldSuggestions: string[];
  customMethod: ICustomMethod = R.Initializer.getCustomMethod();
  loadModes: string[] = R.LoadModes;
  selection: ILoadMapping;
  loadTypes: string[] = R.LoadTypes;
  entity: IEntity = R.Initializer.getEntity();

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public dialogService: DialogService, public windowService: WindowService) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.customMethod, config.data.customMethod);
    }
  }

  ngOnInit() {
    const EntityWindow = this.windowService.windowStore.getCurrentWindow().component.instance as EntityWindowComponent;
    Object.assign(this.entity, EntityWindow.entity);
  }

  saveGroup() {
    this.ref.close(this.customMethod);
  }

  onEditParameters(LoadParameters: ILoadParameter[]) {
    const ref = this.dialogService.open(LoadParamterInfoDialogComponent, {
      data: {
        loadParameters: LoadParameters,
      },
      header: 'Object Information',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((loadParameters: ILoadParameter[]) => {
      if (loadParameters) {
        Object.assign(LoadParameters, loadParameters);
      }
    });
  }

  addLoadMappingRow() {
    this.customMethod.loadMapping.push(R.Initializer.getLoadMapping());
  }

  removeCustomMapping() {
    if (this.selection) {
      const index = this.customMethod.loadMapping.indexOf(this.selection);
      this.customMethod.loadMapping.splice(index, 1);
    }
  }

  getData() {
    this.mFieldSuggestions = this.entity.businessObject.objectMethods.map((method) => {
      return method.name;
    });
  }

}
