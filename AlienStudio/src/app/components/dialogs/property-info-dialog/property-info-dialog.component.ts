import {Component, OnInit} from '@angular/core';
import {IProperty} from '../../../models/Enitity/IProperty';
import {R} from '../../../common/R';
import {ISymbol} from '../../../models/Enitity/ISymbol';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';

@Component({
  selector: 'app-property-info-dialog',
  templateUrl: './property-info-dialog.component.html',
  styleUrls: ['./property-info-dialog.component.css']
})
export class PropertyInfoDialogComponent implements OnInit {

  property: IProperty = R.Initializer.getProperty();
  mFieldSuggestions: ISymbol[];
  mode: number;
  DataTypes = R.DataTypes;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService, public windowService: WindowService) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.property, config.data.property);
    }
  }

  ngOnInit() {

  }


  saveColumn() {
    this.ref.close(this.property);

  }

  filterSymbols(event) {
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event, R.SymbolTypes.TYPE_VARIBLE).subscribe(
      (res) => {
        this.mFieldSuggestions = res;
      }, (err) => {
        console.log(err);
      }
    );
  }

}
