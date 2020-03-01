import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {DragDropHelper} from '../../../common/DragDropHelper';
import {R} from '../../../common/R';
import {PropertyInfo} from '../../../common/PropertyInfo';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';
import {WindowService} from '../../../services/window/window.service';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {IForm} from '../../../models/UI/IForm';
import {ISymbol} from '../../../models/Enitity/ISymbol';
import {NavigationParameterDialogComponent} from '../../dialogs/navigation-parameter-dialog/navigation-parameter-dialog.component';
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
    this.httpClientService.getEntityFields(this.form.entity, event)
      .toPromise()
      .then((result) => {
        this.mFieldSuggestions = result as  ISymbol[];
      });
  }

  OpenNavigationParameterDialog() {
    const ref = this.dialogService.open(NavigationParameterDialogComponent, {
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
    });
  }

  visibleEntityField() {
    return this.PropertyInfo.PropertiesObject.type === R.Controls.TYPE_LABEL || R.Controls.TYPE_INPUT;
  }

}
