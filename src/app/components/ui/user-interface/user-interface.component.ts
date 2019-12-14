import {Component, OnInit, ViewChild} from '@angular/core';
import {WindowBase} from '../../window/window-base/WindowBase';
import {IView} from '../../../models/UI/IView';
import {SideBarComponent} from '../side-bar/side-bar.component';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent extends WindowBase implements OnInit {
  @ViewChild(SideBarComponent, {static: false})
  sidebarComponent: SideBarComponent;
  PropertiesObject: IView = new IView();

  constructor() {
    super();
  }

  ngOnInit() {
  }

  openPropertiesTab() {
    this.sidebarComponent.openPropertiesTab();
  }

  openProperties(view: IView) {
    this.PropertiesObject = view;
  }

}
