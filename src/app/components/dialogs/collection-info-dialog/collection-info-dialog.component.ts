import {Component, OnInit} from '@angular/core';
import {ICollection} from '../../../models/ICollection';
import {NameCode} from '../../../common/NameCode';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {R} from '../../../common/R';

@Component({
  selector: 'app-collection-info-dialog',
  templateUrl: './collection-info-dialog.component.html',
  styleUrls: ['./collection-info-dialog.component.css']
})
export class CollectionInfoDialogComponent implements OnInit {
  collection: ICollection = {entity: '', name: '', objectField: '', dataType: {name: '', code: ''}};
  Lists: string[];
  mFieldSuggestions: string[];
  public CollectionTypes: NameCode[] = R.CollectionTypes;
  mode: number;

  constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) {
    this.mode = config.data.mode;
    if (this.mode === R.Constants.OpenMode.MODE_UPDATE) {
      Object.assign(this.collection, config.data.collection);
    } else {
      this.collection = {
        name: '',
        entity: '',
        objectField: '',
        dataType: {name: '', code: ''}
      };
    }
    this.Lists = config.data.lists;
  }

  ngOnInit() {
  }

  saveColumn() {
    this.ref.close(this.collection);
  }

  filterLists(event) {
    this.mFieldSuggestions = [];
    for (const field of this.Lists) {
      if (field.toLowerCase().indexOf(event.query.toLowerCase()) >= 0) {
        this.mFieldSuggestions.push(field);
      }
    }
  }

}
