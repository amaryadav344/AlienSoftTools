import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {IValidation} from '../../../models/IValidation';
import {IVRule} from '../../../models/IVRule';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {RuleInfoDialogComponent} from '../../dialogs/rule-info-dialog/rule-info-dialog.component';
import {R} from '../../../common/R';
import {IGroup} from '../../../models/IGroup';
import {GroupInfoDialogComponent} from '../../dialogs/group-info-dialog/group-info-dialog.component';

@Component({
  selector: 'app-validation-tab',
  templateUrl: './validation-tab.component.html',
  styleUrls: ['./validation-tab.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ValidationTabComponent implements OnInit {
  @Input() validation: IValidation;
  selection: IVRule = {
    label: '',
    value: '',
    expression: '',
    isDeleteRule: false,
    isHardError: false,
    isInitialLoad: false,
    isObjectRule: false,
    isUpdateRule: false,
    message: {message: '', messageId: 1, parameters: [{label: '', objectField: ''}]}
  };
  groupSelection: IGroup = {label: '', value: ''}

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  openRuleInfo(Mode) {
    const ref = this.dialogService.open(RuleInfoDialogComponent, {
      data: {
        rule: this.selection,
        rules: this.validation.rules,
        mode: Mode
      },
      header: 'Object Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((rule: IVRule) => {
      if (rule) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, rule);
        } else {
          this.validation.rules.push(rule);
        }
      }
    });
  }

  openGroupInfo(Mode) {
    const ref = this.dialogService.open(GroupInfoDialogComponent, {
      data: {
        group: this.groupSelection,
        groups: this.validation.groups,
        mode: Mode
      },
      header: 'Object Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((group: IGroup) => {
      if (group) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.groupSelection, group);
        } else {
          this.validation.groups.push(group);
          this.validation.groups = [...this.validation.groups];
        }
      }
    });
  }

}
