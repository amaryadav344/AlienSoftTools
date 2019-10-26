import {ComponentRef} from '@angular/core';
import {IFile} from '../models/IFile';

export class WindowItem {


  constructor(public component: ComponentRef<any>, public data: IFile) {

  }
}
