import {Component, OnInit} from '@angular/core';
import {IFile} from '../../models/Enitity/IFile';
import {WindowService} from '../../services/window/window.service';
import {WindowItem} from '../../common/window-Item';
import {R} from '../../common/R';
import {HttpClientService} from '../../services/entity-service/httpclient.service';
import {FileStore} from '../../common/FileStore';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  files: IFile[] = [];
  selection: IFile = R.Initializer.getFile();
  EntityPopup = false;
  FormPopup = false;
  WindowsPopup = false;
  windows: WindowItem[];
  selectedWindow: WindowItem = {data: {name: '', type: 0, path: ''}, component: null};
  EntityFiles: IFile[] = [];
  FormFiles: IFile[] = [];

  constructor(public httpClientService: HttpClientService, public windowService: WindowService) {
  }

  ngOnInit() {
    this.windows = this.windowService.windowStore.getWindows();
    this.httpClientService.getFiles().subscribe(
      (res) => {
        FileStore.getInstance().setFiles(res);
        this.EntityFiles = FileStore.getInstance().getFiles().filter(x => x.name.includes('.ent.xml'));
        this.FormFiles = FileStore.getInstance().getFiles().filter(x => x.name.includes('.form.xml'));
      }, (err) => {
        console.log(err);
      }
    );
    this.windowService.SyncFiles$.subscribe((file) => {
      FileStore.getInstance().addFile(file);
      this.EntityFiles = FileStore.getInstance().getFiles().filter(x => x.name.includes('.ent.xml'));
      this.FormFiles = FileStore.getInstance().getFiles().filter(x => x.name.includes('.form.xml'));
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
