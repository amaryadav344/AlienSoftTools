import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {ICustomMethod} from '../../../models/ICustomMethod';
import {ILoadMapping} from '../../../models/ILoadMapping';
import {ILoadParameter} from '../../../models/ILoadParameter';
import {LoadParamterInfoDialogComponent} from '../load-paramter-info-dialog/load-paramter-info-dialog.component';

@Component({
  selector: 'app-custom-method-info-dialog',
  templateUrl: './custom-method-info-dialog.component.html',
  styleUrls: ['./custom-method-info-dialog.component.css'],
  providers: [DialogService],

})
export class CustomMethodInfoDialogComponent implements OnInit {

  customMethod: ICustomMethod = {
    name: '',
    mode: 'All',
    loadPrimaryObject: false,
    loadMapping: []
  };
  loadModes: string[] = R.LoadModes;
  selection: ILoadMapping;
  loadTypes: string[] = R.LoadTypes;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public dialogService: DialogService) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.customMethod, config.data.customMethod);
    }
  }

  ngOnInit() {
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
    this.customMethod.loadMapping.push({name: '', loadParameters: [], loadType: ''});
  }

  removeCustomMapping() {
    if (this.selection) {
      const index = this.customMethod.loadMapping.indexOf(this.selection);
      this.customMethod.loadMapping.splice(index, 1);
    }
  }

}
