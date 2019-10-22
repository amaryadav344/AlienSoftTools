import {Component, OnInit, ViewChild} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {IFile} from '../../models/IFile';
import {OverlayPanel} from 'primeng/primeng';
import {EntityService} from '../../services/entity-service/entity.service';
import {WindowService} from '../../services/window/window.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  items1: MenuItem[];
  files: IFile[] = [];
  @ViewChild('opEntity', {static: false})
  opEntity: OverlayPanel;
  selection: IFile = {name: '', type: 0, path: ''};
  EntityPopup = false;

  constructor(public entityService: EntityService, public windowService: WindowService) {
  }

  ngOnInit() {
    this.entityService.getFiles().subscribe(
      (res) => {
        this.files = res;
      }, (err) => {
        console.log(err);
      }
    );
    this.items1 = [
      {label: 'Stats', icon: 'pi pi-sitemap', command: this.openEntityDropdown},
      {label: 'Calendar', icon: 'fa fa-fw fa-calendar'},
      {label: 'Documentation', icon: 'fa fa-fw fa-book'},
      {label: 'Support', icon: 'fa fa-fw fa-support'},
      {label: 'Social', icon: 'fa fa-fw fa-twitter'}
    ];
  }

  openEntityDropdown(event) {
    this.opEntity.toggle(event);
  }

  loadMoreFiles(event) {

  }

  openFile(event) {
    this.windowService.Openwindow(this.selection);
  }

}
