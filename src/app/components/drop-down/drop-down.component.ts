import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-drop-down',
  templateUrl: './drop-down.component.html',
  styleUrls: ['./drop-down.component.css']
})
export class DropDownComponent implements OnInit {
  @Input() model: string;
  @Input() options: string[];
  @Input() disabled = false;

  constructor() {
  }

  ngOnInit() {
  }

}
