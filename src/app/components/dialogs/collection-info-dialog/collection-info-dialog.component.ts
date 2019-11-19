import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ICollection} from '../../../models/Enitity/ICollection';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {AutoComplete} from 'primeng/primeng';
import {ISymbol} from '../../../models/Enitity/ISymbol';

@Component({
  selector: 'app-collection-info-dialog',
  templateUrl: './collection-info-dialog.component.html',
  styleUrls: ['./collection-info-dialog.component.css']
})
export class CollectionInfoDialogComponent implements OnInit, AfterViewInit {
  collection: ICollection = R.Initializer.getCollection();
  Lists: string[];
  mFieldSuggestions: ISymbol[];
  public CollectionTypes: string[] = R.CollectionTypes;
  mode: number;
  prevalue = '';
  @ViewChild('auto', {static: false})
  ObjectFieldAutoC: AutoComplete;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService, public windowService: WindowService) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.collection, config.data.collection);
    } else {
      this.collection = R.Initializer.getCollection();
    }
    this.Lists = config.data.lists;
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.ObjectFieldAutoC.writeValue({name: this.collection.objectField});
  }

  saveColumn() {
    this.ref.close(this.collection);
  }

  filterSymbols(event) {
    this.prevalue = event.query;
    this.httpClientService.getSymbols(this.windowService.windowStore.getCurrentWindow().data, event.query, R.SymbolTypes.TYPE_COLLECTION).subscribe(
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
    this.collection.objectField = PreText + event.name;
    this.collection.entity = event.entityName;
  }

}
