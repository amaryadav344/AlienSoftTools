import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {ILoadMapping} from "../../../models/Enitity/ILoadMapping";
import {R} from "../../../common/R";
import {IEntity} from "../../../models/Enitity/IEntity";
import {WindowService} from "../../../services/window/window.service";
import {EntityWindowComponent} from "../../entity/entity-window.component";
import {ILoadParameter} from "../../../models/Enitity/ILoadParameter";
import {AutoComplete} from "primeng/primeng";

@Component({
  selector: 'app-load-paramter-info-dialog',
  templateUrl: './load-paramter-info-dialog.component.html',
  styleUrls: ['./load-paramter-info-dialog.component.css'],
})
export class LoadParamterInfoDialogComponent implements OnInit {

  loadMappings: ILoadMapping[] = [];
  mFieldSuggestions: any[];
  entity: IEntity = R.Initializer.getEntity();
  @ViewChild('auto', {static: false})
  EntityFieldAutoC: AutoComplete;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public windowService: WindowService) {
    Object.assign(this.loadMappings, config.data.loadMapping);
  }

  ngOnInit() {
    const EntityWindow = this.windowService.windowStore.getCurrentWindow().component.instance as EntityWindowComponent;
    Object.assign(this.entity, EntityWindow.entity);
  }

  saveLoadParameter() {
    this.ref.close(this.loadMappings);
  }

  filterEntityField(event) {
    this.mFieldSuggestions = [];
    this.mFieldSuggestions
      .push(...this.entity.columns
        .filter(x => x.name.toLowerCase().includes(event.query.toLowerCase()))
        .map((Column) => {
          return {name: Column.name};
        }));
    this.mFieldSuggestions
      .push(...this.entity.objects
        .filter(x => x.name.toLowerCase().includes(event.query.toLowerCase()))
        .map((Object) => {
          return {name: Object.name};
        }));
  }

  onEntityFieldSelected(event, loadParameter: ILoadParameter) {
    loadParameter.entityField = event.name;
  }
}
