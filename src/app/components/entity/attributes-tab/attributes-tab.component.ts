import {Component, Input, OnInit} from '@angular/core';
import {IColumn} from '../../../models/IColumn';
import {IObject} from '../../../models/IObject';
import {ICollection} from '../../../models/ICollection';

@Component({
  selector: 'app-attributes-tab',
  templateUrl: './attributes-tab.component.html',
  styleUrls: ['./attributes-tab.component.css']
})
export class AttributesTabComponent implements OnInit {
  @Input() columns: IColumn[];
  @Input() objects: IObject[];
  @Input() collections: ICollection[];

  constructor() {
  }

  ngOnInit() {
  }

}
