import {Component, OnInit} from '@angular/core';
import {IFile} from '../../models/Enitity/IFile';
import {WindowService} from '../../services/window/window.service';
import {WindowItem} from '../../common/window-Item';
import {R} from '../../common/R';
import {HttpClientService} from '../../services/entity-service/httpclient.service';
import {FileStore} from '../../common/FileStore';
import {DialogService, DynamicDialogConfig} from 'primeng/api';
import {NewFrameDialogComponent} from '../dialogs/new-frame-dialog/new-frame-dialog.component';
import {NewEntityDialogComponent} from '../dialogs/new-entity-dialog/new-entity-dialog.component';

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
  NewItemPopup = false;
  windows: WindowItem[];
  selectedWindow: WindowItem = {data: {name: '', type: 0, path: ''}, component: null};
  EntityFiles: IFile[] = [];
  FormFiles: IFile[] = [];
  items: string[] = ['Entity', 'Form'];

  constructor(public httpClientService: HttpClientService, public windowService: WindowService,
              public dialogService: DialogService) {
  }

  ngOnInit() {
    this.windows = this.windowService.windowStore.getWindows();
    this.httpClientService.getFiles().subscribe(
      (res) => {
        FileStore.getInstance().setFiles(res);
        this.EntityFiles = FileStore.getInstance().getEntityFiles();
        this.FormFiles = FileStore.getInstance().getFormFiles();
      }, (err) => {
        console.log(err);
      }
    );
    this.windowService.SyncFiles$.subscribe((file) => {
      FileStore.getInstance().addFile(file);
      this.EntityFiles = FileStore.getInstance().getEntityFiles();
      this.FormFiles = FileStore.getInstance().getFormFiles();
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

  refreshMetaData() {
    this.httpClientService.RefreshMetaData().toPromise().then();
  }

  CreateNewItem(item) {
    switch (item) {
      case 'Entity':
        this.CreateNewEntity();
        break;
      case  'Form':
        this.CreateNewFrame();
        break;
    }

  }

  CreateNewEntity() {
    const ref = this.dialogService.open(NewEntityDialogComponent, {
      data: {},
      header: 'New Entity',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((file: IFile) => {
      if (file) {
        this.windowService.Openwindow(file);
        this.windowService.syncFiles(file);
      }
    });
  }

  CreateNewFrame() {
    const ref = this.dialogService.open(NewFrameDialogComponent, {
      data: {},
      header: 'New Frame',
      width: '50%',
      contentStyle: {'max-height': '700px', overflow: 'auto'}
    } as DynamicDialogConfig);

    ref.onClose.subscribe((file: IFile) => {
      if (file) {
        this.windowService.Openwindow(file);
        this.windowService.syncFiles(file);
      }
    });
  }

}
