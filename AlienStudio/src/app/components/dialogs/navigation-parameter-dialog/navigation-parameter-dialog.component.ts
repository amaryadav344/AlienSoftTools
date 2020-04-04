import {Component, OnInit} from '@angular/core';
import {NavigationParameter} from '../../../models/UI/NavigationParameter';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {ISymbol} from '../../../models/Enitity/ISymbol';

@Component({
  selector: 'app-navigation-parameter-dialog',
  templateUrl: './navigation-parameter-dialog.component.html',
  styleUrls: ['./navigation-parameter-dialog.component.css']
})
export class NavigationParameterDialogComponent implements OnInit {
  navigationParameters: NavigationParameter[] = [];
  entity = '';
  mFieldSuggestions: any;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig, public httpClientService: HttpClientService) {
    Object.assign(this.navigationParameters, config.data.navigationParameters);
    this.entity = config.data.entity;

  }

  ngOnInit() {
  }

  saveNavigationParameter() {
    this.ref.close(this.navigationParameters);
  }

  filterSymbols(event) {
    this.httpClientService.getEntityFields(this.entity, event)
      .toPromise()
      .then((result) => {
        this.mFieldSuggestions = result as  ISymbol[];
      });
  }


}
