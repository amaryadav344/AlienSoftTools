import {Component, Input, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {IValidation} from '../../../models/IValidation';
import {IVRule} from '../../../models/IVRule';
import {DialogService, DynamicDialogConfig, MenuItem} from 'primeng/api';
import {RuleInfoDialogComponent} from '../../dialogs/rule-info-dialog/rule-info-dialog.component';
import {R} from '../../../common/R';
import {IGroup} from '../../../models/IGroup';
import {IRule} from '../../../models/IRule';
import {GroupInfoDialogComponent} from '../../dialogs/group-info-dialog/group-info-dialog.component';
import {ContextMenu} from 'primeng/primeng';
import {TabChangeServiceService} from '../../../services/tab-change/tab-change-service.service';


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
  itemsInitialLoad: MenuItem[];
  itemsSofterror: MenuItem[];
  itemsHardError: MenuItem[];
  itemsUpdateRule: MenuItem[];
  itemsDeleteRule: MenuItem[];
  itemsGroup: MenuItem[];
  itemsGroupRule: MenuItem[];
  draggedRule: IVRule;
  groupSelection: IGroup = {name: '', rules: []};
  @ViewChild('cmGroup', {static: false}) cmGroup: ContextMenu;
  @ViewChild('cmGroupRule', {static: false}) cmGroupRule: ContextMenu;
  visibleCodeEditor = false;

  constructor(public dialogService: DialogService, public tabChangeService: TabChangeServiceService) {

  }

  ngOnInit() {
    this.initContextMenuItems();
    this.tabChangeService.TabChange$.subscribe((index) => {
      if (index === 2) {
        this.visibleCodeEditor = true;
      }
    });
  }

  openRuleInfo(Mode) {
    const ref = this.dialogService.open(RuleInfoDialogComponent, {
      data: {
        rule: this.selection,
        rules: this.validation.rules,
        mode: Mode
      },
      header: 'Rule Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((rule: IVRule) => {
      if (rule) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, rule);
        } else {
          if (!this.validation.rules) {
            this.validation.rules = [];
          }
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


  openGroupInfo(Mode) {
    const ref = this.dialogService.open(GroupInfoDialogComponent, {
      data: {
        group: this.groupSelection,
        groups: this.validation.groupRules,
        mode: Mode
      },
      header: 'Group Information',
      width: '40%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((group: IGroup) => {
      if (group) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.groupSelection, group);
        } else {
          if (!this.validation.groupRules) {
            this.validation.groupRules = [];
          }
          this.validation.groupRules.push(group);
          this.validation.groupRules = [...this.validation.groupRules];
        }
      }
    });
  }

  removeGroupRule() {
    const index = this.validation.groupRules.indexOf(this.groupSelection);
    const ruleIndex = this.validation.groupRules[index].rules.indexOf(this.IRuleSelection);
    this.validation.groupRules[index].rules.splice(ruleIndex, 1);
  }

  deleteGroup() {
    const index = this.validation.groupRules.indexOf(this.groupSelection);
    this.validation.groupRules.splice(index, 1);
  }

  removeDeleteRule() {
    const index = this.validation.deleteRules.indexOf(this.IRuleSelection);
    this.validation.deleteRules.splice(index, 1);
  }

  removeUpdateRule() {
    const index = this.validation.updateRules.indexOf(this.IRuleSelection);
    this.validation.updateRules.splice(index, 1);
  }

  removeHardRule() {
    const index = this.validation.hardErrors.indexOf(this.IRuleSelection);
    this.validation.hardErrors.splice(index, 1);
  }

  removeSoftRule() {
    const index = this.validation.softErrors.indexOf(this.IRuleSelection);
    this.validation.softErrors.splice(index, 1);
  }

  removeInitialRule() {
    const index = this.validation.initialLoad.indexOf(this.IRuleSelection);
    this.validation.initialLoad.splice(index, 1);
  }

  showGroupContextMenu(event) {
    this.cmGroup.show(event);
  }

  showGroupRuleContextMenu(event) {
    this.cmGroupRule.show(event);
  }

  initContextMenuItems() {
    this.items = [
      {label: 'Rename Rule', icon: 'pi pi-search', command: (event) => this.openRuleInfo(1)},
      {label: 'Delete Rule', icon: 'pi pi-times', command: (event) => this.deleteRule()}
    ];
    this.itemsInitialLoad = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeInitialRule()}
    ];
    this.itemsSofterror = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeSoftRule()}
    ];
    this.itemsHardError = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeHardRule()}
    ];
    this.itemsUpdateRule = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeUpdateRule()}
    ];
    this.itemsDeleteRule = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeDeleteRule()}
    ];
    this.itemsGroup = [
      {label: 'Rename group', icon: 'pi pi-search', command: (event) => this.openGroupInfo(1)},
      {label: 'Delete group', icon: 'pi pi-times', command: (event) => this.deleteGroup()}
    ];
    this.itemsGroupRule = [
      {label: 'Remove Rule', icon: 'pi pi-times', command: (event) => this.removeGroupRule()}
    ];
  }

  initItemsIfNotDefined() {
    if (!this.validation) {
      this.validation = {
        rules: [],
        groupRules: [],
        deleteRules: [],
        hardErrors: [],
        softErrors: [],
        initialLoad: [],
        updateRules: []
      };
    }
    if (!this.validation.rules) {
      this.validation.rules = [];
    }
    if (!this.validation.deleteRules) {
      this.validation.deleteRules = [];
    }
    if (!this.validation.updateRules) {
      this.validation.updateRules = [];
    }
    if (!this.validation.initialLoad) {
      this.validation.initialLoad = [];
    }
    if (!this.validation.softErrors) {
      this.validation.softErrors = [];
    }
    if (!this.validation.hardErrors) {
      this.validation.hardErrors = [];
    }
    if (!this.validation.deleteRules) {
      this.validation.deleteRules = [];
    }
    if (!this.validation.groupRules) {
      this.validation.groupRules = [];
    }


  }
}
