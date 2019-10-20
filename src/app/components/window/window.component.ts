import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ContentDirective} from '../../directives/content.directive';
import {WindowService} from '../../services/window/window.service';
import {EntityWindowComponent} from '../entity/entity-window.component';
import {IFile} from "../../models/IFile";
import {HomeWindowComponent} from "../home-window/home-window.component";

@Component({
  selector: 'app-window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.css']
})
export class WindowComponent implements OnInit, AfterViewInit {
  windows = [];
  @ViewChild(ContentDirective, {static: true}) contentHost: ContentDirective;
  interval: any;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, public windowService: WindowService) {
  }

  ngOnInit() {
    // this.openWindow({path: 'Person\\entPerson.xml', type: 1});
    this.loadHomeComponent();
    this.windowService.OpenWindow$.subscribe((file) => {
      this.openWindow(file);
    });
  }

  ngAfterViewInit() {

  }


  loadHomeComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(HomeWindowComponent);

    const viewContainerRef = this.contentHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    // (<HomeWindowComponent>componentRef.instance).data = adItem.data;
    this.windows.push(componentRef);
  }

  openWindow(file: IFile) {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(EntityWindowComponent);

    const viewContainerRef = this.contentHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (componentRef.instance as EntityWindowComponent).file = file;
    this.windows.push(componentRef);
  }
}
