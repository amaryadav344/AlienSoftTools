import {Component, OnInit} from '@angular/core';
import {R} from '../../../common/R';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng/api';
import {HttpClientService} from '../../../services/entity-service/httpclient.service';

@Component({
  selector: 'app-new-frame-dialog',
  templateUrl: './new-frame-dialog.component.html',
  styleUrls: ['./new-frame-dialog.component.css']
})
export class NewFrameDialogComponent implements OnInit {
  form = R.Initializer.getForm();
  path: string;
  folders: string[] = [];
  entities: string[] = [];

  constructor(public ref: DynamicDialogRef,
              public config: DynamicDialogConfig, public httpClientService: HttpClientService) {
  }

  ngOnInit() {
  }

  getMatchingFolders(query) {
    this.httpClientService.getMatchingFolders(query).subscribe(
      (folders) => {
        this.folders = folders;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getMatchingEntities(query) {
    this.httpClientService.listEntities(query).subscribe(
      (entities) => {
        this.entities = entities;
      },
      (err) => {
        console.log(err);
      }
    );
  }


  OnWizFinish() {
    this.httpClientService.createNewXml(this.form, this.path, false).subscribe(
      (res) => {
        this.ref.close({
          path: this.path + '/' + this.form.name + '.form.xml',
          name: this.form.name + '.form.xml',
          type: 1
        });
      }, (err) => {
        console.log(err);
      }
    );
  }


}
