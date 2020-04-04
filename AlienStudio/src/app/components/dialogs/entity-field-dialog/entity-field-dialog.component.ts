import {Component, OnInit} from '@angular/core';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';

@Component({
  selector: 'app-entity-field-dialog',
  templateUrl: './entity-field-dialog.component.html',
  styleUrls: ['./entity-field-dialog.component.css']
})
export class EntityFieldDialogComponent implements OnInit {
  selection: any;
  options: any;
  entityField = '';
  entity = '';

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig,
              public httpClientService: HttpClientService) {
    Object.assign(this.entityField, config.data.entityField);
    Object.assign(this.entity, config.data.entity);
  }

  ngOnInit() {

  }

}
