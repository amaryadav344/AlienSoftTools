import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IForm} from '../../../models/UI/IForm';

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
  @Input() form: IForm;
  view = false;

  constructor() {
  }

  ngOnInit() {
  }

}
