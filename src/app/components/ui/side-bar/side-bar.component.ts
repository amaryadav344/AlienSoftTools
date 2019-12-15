import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SideBarComponent implements OnInit {
  @Input()
  PropertiesObject: any;
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
