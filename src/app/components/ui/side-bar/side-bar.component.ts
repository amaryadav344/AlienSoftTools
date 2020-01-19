import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {DragDropHelper} from '../../../common/DragDropHelper';
import {R} from '../../../common/R';
import {PropertyInfo} from '../../../common/PropertyInfo';

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


  constructor() {
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

}
