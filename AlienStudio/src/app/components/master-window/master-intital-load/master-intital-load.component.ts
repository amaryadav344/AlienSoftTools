import {Component, OnInit} from '@angular/core';
import {MasterWindowCallBacks} from '../../../common/MasterWindowCallBacks';
import {HttpClientService} from "../../../services/entity-service/httpclient.service";

@Component({
  selector: 'app-master-intital-load',
  templateUrl: './master-intital-load.component.html',
  styleUrls: ['./master-intital-load.component.css']
})
export class MasterIntitalLoadComponent implements OnInit {
  MasterWindowCallbacks: MasterWindowCallBacks;

  constructor(public httpClient: HttpClientService) {
  }

  ngOnInit() {
    this.httpClient.LoadProject().toPromise().then(
      () => {
        this.MasterWindowCallbacks.onInitialResourceLoaded();
      });

  }

}
