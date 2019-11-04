import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DialogService, MessageService} from 'primeng/api';
import {IEntity} from '../../models/Enitity/IEntity';
import {WindowService} from '../../services/window/window.service';
import {WindowBase} from '../window/window-base/WindowBase';
import {R} from '../../common/R';
import {HttpClientService} from '../../services/entity-service/httpclient.service';


@Component({
  selector: 'app-entity-window',
  templateUrl: './entity-window.component.html',
  styleUrls: ['./entity-window.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EntityWindowComponent extends WindowBase implements OnInit {
  entity: IEntity = R.Initializer.getEntity();


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
    if (!this.entity.columns) {
      this.entity.columns = [];
    }
    if (!this.entity.objects) {
      this.entity.objects = [];
    }
    if (!this.entity.collections) {
      this.entity.collections = [];
    }
    if (!this.entity.validation) {
      this.entity.validation = {
        rules: [],
        groupRules: [],
        deleteRules: [],
        hardErrors: [],
        softErrors: [],
        initialLoad: [],
        updateRules: []
      };
    }
    if (!this.entity.validation.rules) {
      this.entity.validation.rules = [];
    }
    if (!this.entity.validation.deleteRules) {
      this.entity.validation.deleteRules = [];
    }
    if (!this.entity.validation.updateRules) {
      this.entity.validation.updateRules = [];
    }
    if (!this.entity.validation.initialLoad) {
      this.entity.validation.initialLoad = [];
    }
    if (!this.entity.validation.softErrors) {
      this.entity.validation.softErrors = [];
    }
    if (!this.entity.validation.hardErrors) {
      this.entity.validation.hardErrors = [];
    }
    if (!this.entity.validation.deleteRules) {
      this.entity.validation.deleteRules = [];
    }
    if (!this.entity.validation.groupRules) {
      this.entity.validation.groupRules = [];
    }
    if (!this.entity.queries) {
      this.entity.queries = [];
    }
    if (!this.entity.businessObject) {
      this.entity.businessObject = {customMethods: [], objectMethods: []};
    }
    if (!this.entity.businessObject.objectMethods) {
      this.entity.businessObject.objectMethods = [];
    }
    if (!this.entity.businessObject.customMethods) {
      this.entity.businessObject.customMethods = [];
    }
  }

  closeWindow() {
    this.windowService.closeWindow(this.file);
  }
}


