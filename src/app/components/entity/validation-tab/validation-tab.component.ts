import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {IValidation} from '../../../models/IValidation';
import {IVRule} from '../../../models/IVRule';
import {DialogService, DynamicDialogConfig, MenuItem} from 'primeng/api';
import {RuleInfoDialogComponent} from '../../dialogs/rule-info-dialog/rule-info-dialog.component';
import {R} from '../../../common/R';
import {IGroup} from '../../../models/IGroup';
import {IRule} from '../../../models/IRule';

@Component({
  selector: 'app-validation-tab',
  templateUrl: './validation-tab.component.html',
  styleUrls: ['./validation-tab.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ValidationTabComponent implements OnInit {
  @Input() validation: IValidation;
  selection: IVRule = {
    name: '',
    expression: '',
    message: {message: '', messageId: 1, parameters: [{label: '', objectField: ''}]}
  };
  IRuleSelection: IRule = {name: ''};
  items: MenuItem[];
  draggedRule: IVRule;

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
    this.items = [
      {label: 'Rename', icon: 'pi pi-search', command: (event) => this.openRuleInfo(1)},
      {label: 'Delete', icon: 'pi pi-times', command: (event) => this.deleteRule()}
    ];
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

  deleteRule() {
  }

  onIVRuleSelected() {
    this.IRuleSelection = {name: this.selection.name};
  }

  onIRuleSelected() {
    for (const Rule of this.validation.rules) {
      if (Rule.name === this.IRuleSelection.name) {
        this.selection = Rule;
        break;
      }
    }
  }

  dragStart(event, ivRule: IVRule) {
    this.draggedRule = ivRule;
  }

  dropInitialLoad() {
    if (this.draggedRule) {
      if (!this.validation.initialLoad.some(x => x.name === this.draggedRule.name)) {
        this.validation.initialLoad.push({name: this.draggedRule.name});
      }
    }
  }

  dropHardError() {
    if (this.draggedRule) {
      if (!this.validation.hardErrors.some(x => x.name === this.draggedRule.name)) {
        this.validation.hardErrors.push({name: this.draggedRule.name});
      }
    }
  }

  dropSoftError() {
    if (this.draggedRule) {
      if (!this.validation.softErrors.some(x => x.name === this.draggedRule.name)) {
        this.validation.softErrors.push({name: this.draggedRule.name});
      }
    }
  }

  dropUpdateRule() {
    if (this.draggedRule) {
      if (!this.validation.updateRules.some(x => x.name === this.draggedRule.name)) {
        this.validation.updateRules.push({name: this.draggedRule.name});
      }
    }
  }

  dropDeleteRule() {
    if (this.draggedRule) {
      if (!this.validation.deleteRules.some(x => x.name === this.draggedRule.name)) {
        this.validation.deleteRules.push({name: this.draggedRule.name});
      }
    }
  }

  dropGroupRule(group: IGroup) {
    if (this.draggedRule) {
      if (!group.rules.some(x => x.name === this.draggedRule.name)) {
        group.rules.push({name: this.draggedRule.name});
      }
    }
  }

  dragEnd(event) {
    this.draggedRule = null;
  }


  /*openGroupInfo(Mode) {
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
  }*/

}
