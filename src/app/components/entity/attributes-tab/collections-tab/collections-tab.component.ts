import {Component, Input, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {ICollection} from '../../../../models/Enitity/ICollection';
import {CollectionInfoDialogComponent} from '../../../dialogs/collection-info-dialog/collection-info-dialog.component';
import {R} from '../../../../common/R';

@Component({
  selector: 'app-collections-tab',
  templateUrl: './collections-tab.component.html',
  styleUrls: ['./collections-tab.component.css'],
})
export class CollectionsTabComponent implements OnInit {
  @Input() collections: ICollection[];
  selection: ICollection;
  Lists: string[] = ['iclbPerson', 'iclbAccount', 'iclbCredits', 'iclbProfile'];

  constructor(public dialogService: DialogService) {
  }

  ngOnInit() {
  }

  deleteCollection(collection: ICollection) {
    const index = this.collections.indexOf(collection);
    this.collections.splice(index, 1);
  }

  viewCollection(collection: ICollection) {
    this.selection = collection;
    this.onCollectionSelect(1);
  }

  onCollectionSelect(Mode: number) {
    const ref = this.dialogService.open(CollectionInfoDialogComponent, {
      data: {
        collection: this.selection,
        lists: this.Lists,
        mode: Mode,
      },
      header: 'Collection Information',
      width:
        '40%',
      contentStyle:
        {
          'max-height':
            '700px', overflow:
          'auto'
        }
    }as DynamicDialogConfig);
    ref.onClose.subscribe((collection: ICollection) => {
      if (collection) {
        if (Mode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, collection);
        } else {
          this.collections.push(collection);
        }
      }
    });
  }

}
