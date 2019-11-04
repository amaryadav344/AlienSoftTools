import {Component, OnInit} from '@angular/core';
import {HttpClientService} from '../../services/entity-service/httpclient.service';
import {IDBConnectionInfo} from '../../models/Enitity/IDBConnectionInfo';
import {R} from '../../common/R';
import {WindowService} from '../../services/window/window.service';
import {WindowItem} from '../../common/window-Item';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  connectionInfo: IDBConnectionInfo = R.Initializer.getDBConnectionInfo();
  windowItem: WindowItem = R.Initializer.getWindowItem();

  constructor(public httpClientService: HttpClientService,
              public windowService: WindowService) {
  }

  ngOnInit() {
    this.httpClientService.getDBConnectionInfo().toPromise()
      .then((connectionInfo) => {
        Object.assign(this.connectionInfo, connectionInfo);
      });
    this.windowItem = this.windowService.windowStore.getCurrentWindow();
    this.windowService.WindowChange$.subscribe((windowItem) => {
      this.windowItem = windowItem as WindowItem;
    });

  }

}
