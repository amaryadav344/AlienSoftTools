import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ContentDirective} from '../../directives/content.directive';
import {HomeWindowComponent} from '../home-window/home-window.component';

@Component({
  selector: 'app-window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.css']
})
export class WindowComponent implements OnInit, AfterViewInit {
  windows = [];
  @ViewChild(ContentDirective, {static: true}) contentHost: ContentDirective;
  interval: any;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
    this.loadHomeComponent();
  }

  ngAfterViewInit() {
  }


  loadHomeComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(HomeWindowComponent);

    const viewContainerRef = this.contentHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    // (<HomeWindowComponent>componentRef.instance).data = adItem.data;
  }
}
