import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {R} from '../../../common/R';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {IObject} from '../../../models/Enitity/IObject';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {AutoComplete} from 'primeng/primeng';
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

  ngAfterViewInit(): void {
    this.ObjectFieldAutoC.writeValue({name: this.object.objectField});
  }

  saveColumn() {
    this.ref.close(this.object);

  }

  filterSymbols(event) {
    this.prevalue = event.query;
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event.query, R.SymbolTypes.TYPE_OBJECT).subscribe(
      (res) => {
        this.mFieldSuggestions = res;
      }, (err) => {
        console.log(err);
      }
    );
  }

  onObjectFieldSelected(event) {
    const PreText = this.prevalue.substr(0, this.prevalue.lastIndexOf('\.') + 1);
    event.name = PreText + event.name;
    this.ObjectFieldAutoC.writeValue(event);
    this.object.objectField = PreText + event.name;
    this.object.entity = event.entityName;
  }


}
