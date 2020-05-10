import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {DragDropHelper} from '../../../common/DragDropHelper';
import {R} from '../../../common/R';
import {PropertyInfo} from '../../../common/PropertyInfo';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {IForm} from '../../../models/UI/IForm';
import {ISymbol} from '../../../models/Enitity/ISymbol';
import {NavigationParameter} from '../../../models/UI/NavigationParameter';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SideBarComponent implements OnInit {
  @Input()
  PropertyInfo: PropertyInfo;
  @Input()
  form: IForm;
  CurrentTabIndex = 0;
  dragDropHelper: DragDropHelper = DragDropHelper.getInstance();
  TextAlignmentOptions = R.TextAlignments;
  TextDecorationsOptions = R.TextDecorations;
  TextTransformsOptions = R.TextTransforms;
  WhiteSpacesOptions = R.WhiteSpaces;
  BoolenValuesOptions = R.BoolenValues;
  OrientationOptions = R.Orientations;
  VerticalAlignmentOptions = R.VerticalAlignments;
  HorizontalAlignmentOptions = R.HorizontalAlignments;
  VisibilityOptions = R.Visibility;
  ButtonTypes = R.ButtonTypes;
  mFormSuggestions: string[];
  mFieldSuggestions: any;

  constructor(public httpClientService: HttpClientService, public windowService: WindowService,
              public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  dragStart(event) {
    this.dragDropHelper.setControl(event.target.id);
  }

  openPropertiesTab() {
    this.CurrentTabIndex = 1;
  }

  isStackLayout(view: any): boolean {
    return view.type === R.Controls.TYPE_STACK_LAYOUT;
  }

  isGridLayout(view: any): boolean {
    return view.type === R.Controls.TYPE_GRID_LAYOUT;
  }

  isScrollView(view: any): boolean {
    return view.type === R.Controls.TYPE_SCROLL_VIEW;
  }

  isButton(view: any): boolean {
    return view.type === R.Controls.TYPE_BUTTON;
  }

  isButtonOpenFormType(view: any): boolean {
    return view.onClick === R.ButtonTypes[0];
  }

  isButtonExecuteBusinessMethod(view: any): boolean {
    return view.onClick === R.ButtonTypes[1];
  }

  getForms(event) {
    this.httpClientService.getForms(event.query)
      .toPromise()
      .then((result) => {
        this.mFormSuggestions = result as string[];
      });
  }

  getNavigationParameters(event) {
    this.PropertyInfo.PropertiesObject.navigationParameters = [];
    this.httpClientService.getNavigationParameters(event)
      .toPromise()
      .then((result) => {
        this.PropertyInfo.PropertiesObject.navigationParameters = result;
      });
  }


  filterSymbols(event) {
    this.httpClientService.getEntityFields(this.form.entity, this.FindParentEntityFieldForControl(this.PropertyInfo.PropertiesObject) + event)
      .toPromise()
      .then((result) => {
        this.mFieldSuggestions = result as  ISymbol[];
      });

  }

  FindParentEntityFieldForControl(child) {
    const ListControl = [];
    this.CheckControl(this.form.control, ListControl);

    if (ListControl.length > 0) {
      for (const control of ListControl) {
        if (this.CheckIfControlHasChild(control, child)) {
          return control.entityField + '.';
        }
      }
    } else {
      return '';
    }
    return '';
  }

  CheckIfControlHasChild(control: any, child: any) {
    if (control === child && child.type !== R.Controls.TYPE_LIST_VIEW) {
      return true;
    } else {
      if (control.control) {
        if (this.CheckIfControlHasChild(control.control, child)) {
          return true;
        }
      }
      if (control.controls) {
        for (const con of control.controls) {
          if (this.CheckIfControlHasChild(con, child)) {
            return true;
          }
        }
      }
    }
    return false;

  }

  CheckControl(control, ListControl) {
    if (control.type === R.Controls.TYPE_LIST_VIEW) {
      ListControl.push(control);
    } else {
      if (control.control) {
        this.CheckControl(control, ListControl);
      }
      if (control.controls) {
        for (const con of control.controls) {
          this.CheckControl(con, ListControl);
        }
      }
    }

  }


  CheckListHasControl(ListView, child) {
    let hascontrol;
    if (ListView.control === child) {
      hascontrol = true;
    } else {
      for (const control of ListView.control.controls) {
        if (control === child) {
          hascontrol = true;
          break;
        }
        hascontrol = this.CheckListHasControl(control, child);
      }
    }
    return hascontrol;
  }

  OpenNavigationParameterDialog() {
    /*const ref = this.dialogService.open(NavigationParameterDialogComponent, {
      data: {
        entity: this.form.entity,
        navigationParameters: this.PropertyInfo.PropertiesObject.navigationParameters,
      },
      header: 'Set navigation parameters',
      width:
        '40%',
      contentStyle:
        {
          'max-height':
            '700px', overflow:
          'auto'
        }
    }as DynamicDialogConfig);
    ref.onClose.subscribe((value: NavigationParameter[]) => {
      if (value) {
        this.PropertyInfo.PropertiesObject.navigationParameters = value;
      }
    });*/
  }

  visibleEntityField() {
    return this.PropertyInfo.PropertiesObject.type === R.Controls.TYPE_LABEL ||
      this.PropertyInfo.PropertiesObject.type === R.Controls.TYPE_INPUT ||
      this.PropertyInfo.PropertiesObject.type === R.Controls.TYPE_LIST_VIEW;
  }


}
