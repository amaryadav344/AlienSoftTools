import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';
import {IVRule} from '../../../models/IVRule';

@Component({
  selector: 'app-rule-info-dialog',
  templateUrl: './rule-info-dialog.component.html',
  styleUrls: ['./rule-info-dialog.component.css']
})
export class RuleInfoDialogComponent implements OnInit {
  rule: IVRule = {
    expression: '',
    value: '',
    label: '',
    message: {
      message: '',
      messageId: 0,
      parameters: [],
    },
    groups: [],
  };

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.rule, config.data.rule);
    }
  }

  ngOnInit() {
  }

  saveRule() {
    this.rule.value = this.rule.label
    this.ref.close(this.rule);
  }

}
