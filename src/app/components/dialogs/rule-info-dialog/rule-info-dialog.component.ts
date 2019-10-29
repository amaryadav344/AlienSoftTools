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
  rule: IVRule = R.Initializer.getVRule();

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    if (config.data.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.rule, config.data.rule);
    }
  }

  ngOnInit() {
  }

  saveRule() {
    this.ref.close(this.rule);
  }

}
