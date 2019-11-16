import {Component, OnInit} from '@angular/core';
import {WindowBase} from '../../window/window-base/WindowBase';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent extends WindowBase implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
