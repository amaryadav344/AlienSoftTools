import {AfterViewInit, Component, OnInit} from '@angular/core';
import {R} from '../../../common/R';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IObject} from '../../../models/Enitity/IObject';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {ISymbol} from '../../../models/Enitity/ISymbol';

@Component({
  selector: 'app-object-info-dialog',
  templateUrl: './object-info-dialog.component.html',
  styleUrls: ['./object-info-dialog.component.css']
})
export class ObjectInfoDialogComponent implements OnInit, AfterViewInit {

  object: IObject = R.Initializer.getObject();
  mFieldSuggestions: ISymbol[];
  mode: number;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService, public windowService: WindowService) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.object, config.data.object);
    }
  }

  ngOnInit() {

  }

  ngAfterViewInit(): void {
  }

  saveColumn() {
    this.ref.close(this.object);

  }

  filterSymbols(event) {
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event, R.SymbolTypes.TYPE_OBJECT).subscribe(
      (res) => {
        this.mFieldSuggestions = res;
      }, (err) => {
        console.log(err);
      }
    );
  }
}
