import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
import {MenuItem} from 'primeng/api';


@Component({
  selector: 'app-entity-toolbar',
  templateUrl: './entity-toolbar.component.html',
  styleUrls: ['./entity-toolbar.component.css']
})
export class EntityToolbarComponent implements OnInit {
  @Input() entity: IEntity;
  @Output() switchToXMl = new EventEmitter<boolean>();
  @Output() switchToUI = new EventEmitter<boolean>();
  @Output() closeWindow = new EventEmitter();
  view = false;
  items2: MenuItem[] = [
    {
      label: 'Open file location',
    },
    {
      label: 'Edit file with sublime',
    }
  ];

  constructor() {
  }

  ngOnInit() {
  }


}
