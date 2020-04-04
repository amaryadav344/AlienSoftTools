import {Component, OnInit} from '@angular/core';
import {MasterWindowCallBacks} from '../../../common/MasterWindowCallBacks';

@Component({
  selector: 'app-master-login-window',
  templateUrl: './master-login-window.component.html',
  styleUrls: ['./master-login-window.component.css']
})
export class MasterLoginWindowComponent implements OnInit {
  MasterWindowCallbacks: MasterWindowCallBacks;

  constructor() {
  }

  ngOnInit() {
  }

}
