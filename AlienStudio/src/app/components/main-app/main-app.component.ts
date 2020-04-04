import {Component, OnInit} from '@angular/core';
import {DialogService, MessageService} from 'primeng/api';

@Component({
  selector: 'app-main-app',
  templateUrl: './main-app.component.html',
  styleUrls: ['./main-app.component.css'],
  providers: [DialogService, MessageService]
})
export class MainAppComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
