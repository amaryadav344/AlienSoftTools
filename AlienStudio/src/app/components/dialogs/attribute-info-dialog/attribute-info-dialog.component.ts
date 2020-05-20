import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {IEntity} from '../../../models/Enitity/IEntity';
import {IAttribute} from '../../../models/Enitity/IAttribute';
import {R} from '../../../common/R';
import {WindowService} from '../../../services/window/window.service';

@Component({
  selector: 'app-attribute-info-dialog',
  templateUrl: './attribute-info-dialog.component.html',
  styleUrls: ['./attribute-info-dialog.component.css']
})
export class AttributeInfoDialogComponent implements OnInit {
  entity: IEntity = R.Initializer.getEntity();
  mode: number;
  attribute: IAttribute = R.Initializer.getAttribute();
  mFieldSuggestions: any;
  DataType = R.DataTypesArray;

  ngOnInit(): void {
  }

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public httpClientService: HttpClientService,
              public  windowService: WindowService) {
    Object.assign(this.entity, config.data.entity);
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      this.attribute = config.data.attribute;
    }

  }

  filterSymbols(event) {
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event, '').subscribe(
      (res) => {
        this.mFieldSuggestions = res;
      }, (err) => {
        console.log(err);
      }
    );
  }

  saveAttribute() {
    this.ref.close(this.attribute);
  }


}
