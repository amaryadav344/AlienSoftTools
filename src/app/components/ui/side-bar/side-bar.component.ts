import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {IView} from '../../../models/UI/IView';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SideBarComponent implements OnInit {
  @Input()
  PropertiesObject: IView;
  CurrentTabIndex = 0;


  constructor() {
  }

  ngOnInit() {
  }

  dragStart(event) {
    event.dataTransfer.setData('Control', event.target.id);
  }

  openPropertiesTab() {
    this.CurrentTabIndex = 1;
  }

}
