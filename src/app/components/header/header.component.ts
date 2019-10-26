import {Component, OnInit} from '@angular/core';
import {IFile} from '../../models/IFile';
import {EntityService} from '../../services/entity-service/entity.service';
import {WindowService} from '../../services/window/window.service';
import {WindowItem} from "../../common/window-Item";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  files: IFile[] = [];
  selection: IFile = {name: '', type: 0, path: ''};
  EntityPopup = false;
  WindowsPopup = false;
  windows: WindowItem[];

  constructor(public entityService: EntityService, public windowService: WindowService) {
  }

  ngOnInit() {
    this.windows = this.windowService.windowStore.getWindows();
    this.entityService.getFiles().subscribe(
      (res) => {
        this.files = res;
      }, (err) => {
        console.log(err);
      }
    );
  }

  loadMoreFiles(event) {

  }

  openFile(event) {
    this.windowService.Openwindow(this.selection);
    this.selection = {name: '', type: 0, path: ''};
  }

}
