import {Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ContentDirective} from '../../directives/content.directive';
import {MasterLoginWindowComponent} from './master-login-window/master-login-window.component';
import {MasterIntitalLoadComponent} from './master-intital-load/master-intital-load.component';
import {MasterWindowCallBacks} from '../../common/MasterWindowCallBacks';
import {MainAppComponent} from "../main-app/main-app.component";

@Component({
  selector: 'app-master-window',
  templateUrl: './master-window.component.html',
  styleUrls: ['./master-window.component.css']
})
export class MasterWindowComponent implements OnInit, MasterWindowCallBacks {
  @ViewChild(ContentDirective, {static: true}) mastercontentHost: ContentDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
    this.initMainAppComponent();
  }

  initLoginComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(MasterLoginWindowComponent);
    const viewContainerRef = this.mastercontentHost.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
    (componentRef.instance as MasterLoginWindowComponent).MasterWindowCallbacks = this;
  }

  initLoadingComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(MasterIntitalLoadComponent);
    const viewContainerRef = this.mastercontentHost.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
    (componentRef.instance as MasterIntitalLoadComponent).MasterWindowCallbacks = this;
  }

  initMainAppComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(MainAppComponent);
    const viewContainerRef = this.mastercontentHost.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
  }

  onLogin() {
    this.initLoadingComponent();
  }

  onInitialResourceLoaded() {
    this.initMainAppComponent();
  }

}
