import {Component, Input, OnInit} from '@angular/core';
import {IColumn} from '../../../../models/Enitity/IColumn';
import {ColumnInfoDialogComponent} from '../../../dialogs/column-info-dialog/column-info-dialog.component';
import {DialogService, DynamicDialogConfig} from 'primeng/api';

@Component({
  selector: 'app-columns-tab',
  templateUrl: './columns-tab.component.html',
  styleUrls: ['./columns-tab.component.css'],
})
export class ColumnsTabComponent implements OnInit {
  @Input() columns: IColumn[];
  selection: IColumn;
  Fields: string[] = ['istrPersonId', 'istrPersonName', 'iintEmailId', 'iintPhoneNumber'];

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  onColumnSelect() {
    const ref = this.dialogService.open(ColumnInfoDialogComponent, {
        data: {
          column: this.selection,
          fields: this.Fields,
        },
        header: 'Column Information',
        width:
          '40%',
        contentStyle:
          {
            'max-height':
              '700px', overflow:
            'auto'
          }
      }as DynamicDialogConfig
      )
    ;
  }

  viewColumn(column: IColumn) {
    this.selection = column;
    this.onColumnSelect();
  }

}
