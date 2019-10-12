import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {IEntity} from '../../../models/IEntity';
import {MenuItem} from 'primeng/api';


@Component({
  selector: 'app-entity-toolbar',
  templateUrl: './entity-toolbar.component.html',
  styleUrls: ['./entity-toolbar.component.css']
})
export class EntityToolbarComponent implements OnInit {
  @Input() entity: IEntity;
  @Output() switch = new EventEmitter<boolean>();
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

  switchToTextMode() {
    this.switch.emit(!this.view);
  }

}
