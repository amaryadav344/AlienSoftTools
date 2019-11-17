import {Component, OnInit} from '@angular/core';
import {IObjectMethod} from '../../../models/Enitity/IObjectMethod';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {WindowService} from '../../../services/window/window.service';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';

@Component({
  selector: 'app-object-method-info-dialog',
  templateUrl: './object-method-info-dialog.component.html',
  styleUrls: ['./object-method-info-dialog.component.css']
})
export class ObjectMethodInfoDialogComponent implements OnInit {

  objectMethod: IObjectMethod = R.Initializer.getObjectMethod();
  suggectionMethods: IObjectMethod[];

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService, public windowService: WindowService) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.objectMethod, config.data.objectMethod);
    }
  }

  ngOnInit() {
  }

  saveObjectMethod() {
    this.ref.close(this.objectMethod);
  }

  getMatchingMethods(query: string) {
    this.httpClientService.getObjectMethods(this.windowService.windowStore.getCurrentWindow().data, query).toPromise()
      .then((result) => {
        this.suggectionMethods = result as IObjectMethod[];
      });
  }

}
