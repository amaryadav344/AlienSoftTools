import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/index';
import {IFile} from '../../models/IFile';

@Injectable({
  providedIn: 'root'
})
export class WindowService {
  private OpenWindow = new Subject<IFile>();
  OpenWindow$ = this.OpenWindow.asObservable();
  private CloseWindow = new Subject<IFile>();
  CloseWindow$ = this.CloseWindow.asObservable();

  constructor() {
  }

  Openwindow(file: IFile) {
    this.OpenWindow.next(file);
  }

  closeWindow(file: IFile) {
    this.CloseWindow.next(file);
  }
}
