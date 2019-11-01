import {Component, OnInit, ViewChild} from '@angular/core';
import {R} from '../../../common/R';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IObject} from '../../../models/IObject';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {AutoComplete} from "primeng/primeng";

@Component({
  selector: 'app-object-info-dialog',
  templateUrl: './object-info-dialog.component.html',
  styleUrls: ['./object-info-dialog.component.css']
})
export class ObjectInfoDialogComponent implements OnInit {

  object: IObject = R.Initializer.getObject();
  mFieldSuggestions: string[];
  mode: number;
  prevalue = '';
  @ViewChild('auto', {static: false})
  ObjectFieldAutoC: AutoComplete;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService, public windowService: WindowService) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.object, config.data.object);
    }
  }

  ngOnInit() {
  }

  saveColumn() {
    this.ref.close(this.object);

  }

  filterSymbols(event) {
    this.prevalue = event.query;
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event.query).subscribe(
      (res) => {
        this.mFieldSuggestions = res;
      }, (err) => {
        console.log(err);
      }
    );
  }

  onObjectFieldSelected(event) {
    const PreText = this.prevalue.substr(0, this.prevalue.lastIndexOf('\.') + 1);
    this.ObjectFieldAutoC.writeValue(PreText + event);
    this.object.objectField = PreText + event;
  }


}
