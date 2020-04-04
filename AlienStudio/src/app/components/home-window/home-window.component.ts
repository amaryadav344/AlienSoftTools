import {Component, OnInit} from '@angular/core';

import {DialogService} from 'primeng/api';
import {HttpClientService} from '../../services/entity-service/httpclient.service';
import {WindowService} from '../../services/window/window.service';
import {WindowBase} from '../window/window-base/WindowBase';

@Component({
  selector: 'app-home-window',
  templateUrl: './home-window.component.html',
  styleUrls: ['./home-window.component.css'],
  providers: [DialogService, HttpClientService],
})
export class HomeWindowComponent extends WindowBase implements OnInit {
  data: any;

  constructor(public dialogService: DialogService, public windowService: WindowService) {
    super();
    this.data = {
      labels: ['Entity', 'Forms'],
      datasets: [
        {
          data: [55, 55],
          backgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56'
          ],
          hoverBackgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56'
          ]
        }]
    };
  }

  ngOnInit() {
  }


}
