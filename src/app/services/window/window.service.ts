import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/index';
import {IFile} from '../../models/IFile';

@Injectable({
  providedIn: 'root'
})
export class WindowService {
  private OpenWindow = new Subject<IFile>();
  OpenWindow$ = this.OpenWindow.asObservable();

  constructor() {
  }

  Openwindow(file: IFile) {
    this.OpenWindow.next(file);
  }
}
