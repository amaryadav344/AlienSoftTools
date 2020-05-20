import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DialogService, DynamicDialogConfig, MessageService} from 'primeng/api';
import {IEntity} from '../../models/Enitity/IEntity';
import {WindowService} from '../../services/window/window.service';
import {WindowBase} from '../window/window-base/WindowBase';
import {R} from '../../common/R';
import {HttpClientService} from '../../services/entity-service/httpclient.service';
import {AttributeInfoDialogComponent} from '../dialogs/attribute-info-dialog/attribute-info-dialog.component';
import {IAttribute} from '../../models/Enitity/IAttribute';


@Component({
  selector: 'app-entity-window',
  templateUrl: './entity-window.component.html',
  styleUrls: ['./entity-window.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EntityWindowComponent extends WindowBase implements OnInit {
  entity: IEntity = R.Initializer.getEntity();
  selection: IAttribute;


  constructor(public dialogService: DialogService, public httpClientService: HttpClientService,
              public windowService: WindowService, public messageService: MessageService) {
    super();
  }

  ngOnInit() {
    this.httpClientService.getFile(this.file).subscribe(
      (entity) => {
        this.entity = entity as IEntity;
        this.checkForUndefined(this.entity);
      }, (error) => {
        console.log(error);
      });
  }


  getXML() {
    this.httpClientService.getXMLFromJS(this.entity).subscribe(
      xml => {
        this.xml = xml;
        this.isTextView = !this.isTextView;
      },
      error => {
        console.log(error);
      });
  }

  getJS() {
    this.httpClientService.getJSFromXML(this.xml).subscribe(
      entity => {
        this.entity = entity as IEntity;
        this.checkForUndefined(this.entity);
        this.isTextView = !this.isTextView;
      },
      error => {
        console.log(error);
      });
  }

  saveXML() {
    this.httpClientService.saveXML(this.entity, this.file.path).subscribe(
      responce => {
        this.messageService.add({severity: 'success', summary: 'Save', detail: 'Saved File ' + this.file.name});
      },
      error => {
        console.log(error);
      });
  }

  tabChanged(e) {
    const index = e.index;
    this.windowService.tabChanged(index);
  }

  checkForUndefined(entity: IEntity) {
    if (!this.entity.attributes) {
      this.entity.attributes = [];
    }
  }

  closeWindow() {
    this.windowService.closeWindow(this.file);
  }

  onAddNewAttribute(Openmode) {
    const ref = this.dialogService.open(AttributeInfoDialogComponent, {
      data: {
        entity: this.entity,
        attribute: this.selection,
        mode: Openmode,
      },
      header: 'Collection Information',
      width:
        '40%',
      contentStyle:
        {
          'max-height':
            '800px', overflow:
          'auto'
        }
    }as DynamicDialogConfig);
    ref.onClose.subscribe((attribute: IAttribute) => {
      if (attribute) {
        if (Openmode === R.Constants.OpenMode.MODE_UPDATE) {
          Object.assign(this.selection, attribute);
        } else {
          this.entity.attributes.push(attribute);
        }
      }
    });
  }
}


