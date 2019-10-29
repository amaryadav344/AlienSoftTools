import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/index';
import {IFile} from '../../models/IFile';
import {WindowStore} from '../../common/WindowStore';

@Injectable({
  providedIn: 'root'
})
export class WindowService {
  private OpenWindow = new Subject<IFile>();
  OpenWindow$ = this.OpenWindow.asObservable();
  private CloseWindow = new Subject<IFile>();
  CloseWindow$ = this.CloseWindow.asObservable();
  windowStore: WindowStore = WindowStore.getInstance();
  private SyncFiles = new Subject<IFile>();
  SyncFiles$ = this.SyncFiles.asObservable();

  constructor() {
  }

  Openwindow(file: IFile) {
    this.OpenWindow.next(file);
  }

  closeWindow(file: IFile) {
    this.CloseWindow.next(file);
  }

  syncFiles(file: IFile) {
    this.SyncFiles.next(file);
  }
}
