import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {IGroup} from '../../../models/IGroup';

@Component({
  selector: 'app-group-info-dialog',
  templateUrl: './group-info-dialog.component.html',
  styleUrls: ['./group-info-dialog.component.css']
})
export class GroupInfoDialogComponent implements OnInit {
  group: IGroup = {name: '', rules: []};

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.group, config.data.group);
    }
  }

  ngOnInit() {
  }

  saveGroup() {
    this.ref.close(this.group);
  }

}
