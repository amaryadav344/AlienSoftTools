import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-uitoolbar',
  templateUrl: './uitoolbar.component.html',
  styleUrls: ['./uitoolbar.component.css']
})
export class UIToolbarComponent implements OnInit {
  @Output() switchToXMl = new EventEmitter<boolean>();
  @Output() switchToUI = new EventEmitter<boolean>();
  @Output() closeWindow = new EventEmitter();
  @Output() saveXML = new EventEmitter();
  view = false;

  constructor() {
  }

  ngOnInit() {
  }

}
