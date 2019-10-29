import {Component, OnInit} from '@angular/core';
import {IFile} from '../../models/IFile';
import {EntityService} from '../../services/entity-service/entity.service';
import {WindowService} from '../../services/window/window.service';
import {WindowItem} from '../../common/window-Item';
import {R} from '../../common/R';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  files: IFile[] = [];
  selection: IFile = R.Initializer.getFile();
  EntityPopup = false;
  WindowsPopup = false;
  windows: WindowItem[];
  selectedWindow: WindowItem = {data: {name: '', type: 0, path: ''}, component: null};

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
    this.windowService.SyncFiles$.subscribe((file) => {
      this.files.push(file);
      this.files = [...this.files];
    });
  }


  openWindow() {
    this.windowService.Openwindow(this.selectedWindow.data);
    this.selectedWindow = {data: {name: '', type: 0, path: ''}, component: null};

  }

  openFile(event) {
    this.windowService.Openwindow(this.selection);
    this.selection = {name: '', type: 0, path: ''};
  }

}
