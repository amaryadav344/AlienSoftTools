import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {DragDropHelper} from '../../../common/DragDropHelper';
import {R} from '../../../common/R';
import {PropertyInfo} from '../../../common/PropertyInfo';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SideBarComponent implements OnInit {
  @Input()
  PropertyInfo: PropertyInfo;
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


  constructor(public httpClientService: HttpClientService) {
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
    this.httpClientService.getNavigationParameters(event)
      .toPromise()
      .then((result) => {
        this.PropertyInfo.PropertiesObject.navigationParameters = result;
      });
  }

}
