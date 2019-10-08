import {Component, Input, OnInit} from '@angular/core';
import {IQuery} from '../../../models/IQuery';
import {NameCode} from '../../../common/NameCode';
import {R} from '../../../common/R';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {QueryInfoDialogComponent} from '../../dialogs/query-info-diaglog/query-info-dialog.component';
import {IParameter} from '../../../models/IParameter';

@Component({
  selector: 'app-query-tab',
  templateUrl: './query-tab.component.html',
  styleUrls: ['./query-tab.component.css'],
  providers: [DialogService]
})
export class QueryTabComponent implements OnInit {
  @Input() queries: IQuery[] = [];
  selection: IQuery = {
    expression: '',
    label: '',
    value: '',
    parameters: [],
    customMaps: [],
  };
  QueryTypes: NameCode[] = R.QueryTypes;
  DataTypes: NameCode[] = R.DataTypes;
  mFieldSuggestions: string[];
  Fields: string[] = ['istrPersonId', 'istrPersonName', 'iintEmailId', 'iintPhoneNumber'];

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {

  }

  openQueryInfo(Query, Mode) {
    const ref = this.dialogService.open(QueryInfoDialogComponent, {
      data: {
        query: Query,
        mode: Mode
      },
      header: 'Query Information',
      width:
        '40%',
      contentStyle:
        {
          'max-height':
            '700px', overflow:
          'auto'
        }
    } as DynamicDialogConfig);

    ref.onClose.subscribe((query: IQuery) => {
      if (query) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(Query, query);
        } else {
          this.queries.push(query);
        }
        this.selection = query;
      }
    });
  }

  refreshParameters() {
    const newParameters: IParameter[] = [];
    const Parameters: string[] = this.selection.expression.match(/@\w+/g);
    if (Parameters) {
      for (const Parameter of Parameters) {
        newParameters.push({name: Parameter, dataType: {name: '', code: ''}});
      }
    }
    const finalParameters: IParameter[] = [];
    for (const Parameter of newParameters) {
      if (this.selection.parameters.some(x => x.name === Parameter.name)) {
        finalParameters.push(this.selection.parameters.find(x => x.name === Parameter.name));
      } else {
        finalParameters.push(Parameter);
      }
    }
    this.selection.parameters = finalParameters;
  }


  filterCountrySingle(event) {
    this.mFieldSuggestions = [];
    for (const field of this.Fields) {
      if (field.toLowerCase().indexOf(event.query.toLowerCase()) >= 0) {
        this.mFieldSuggestions.push(field);
      }
    }
  }

}
