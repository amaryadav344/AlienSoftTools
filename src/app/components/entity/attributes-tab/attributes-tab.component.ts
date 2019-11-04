import {Component, Input, OnInit} from '@angular/core';
import {IColumn} from '../../../models/Enitity/IColumn';
import {IObject} from '../../../models/Enitity/IObject';
import {ICollection} from '../../../models/Enitity/ICollection';

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
