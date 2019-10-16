import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class TabChangeServiceService {
  private TabChange = new Subject<number>();
  TabChange$ = this.TabChange.asObservable();

  constructor() {
  }

  tabChanged(index: number) {
    this.TabChange.next(index);
  }
}
