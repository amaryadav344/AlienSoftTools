import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ContentDirective} from '../../directives/content.directive';
import {WindowService} from '../../services/window/window.service';
import {EntityWindowComponent} from '../entity/entity-window.component';
import {IFile} from '../../models/IFile';
import {HomeWindowComponent} from '../home-window/home-window.component';
import {WindowItem} from '../../common/window-Item';
import {WindowBase} from './window-base/WindowBase';

@Component({
  selector: 'app-window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.css']
})
export class WindowComponent implements OnInit, AfterViewInit {
  @ViewChild(ContentDirective, {static: true}) contentHost: ContentDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, public windowService: WindowService) {
  }

  ngOnInit() {
    // this.openWindow({path: 'Person\\entPerson.xml', type: 1});
    this.loadHomeComponent();
    this.windowService.OpenWindow$.subscribe((file) => {
      this.openWindow(file);
    });
    this.windowService.CloseWindow$.subscribe((file) => {
      this.closeWindow(file);
    });
  }

  ngAfterViewInit() {

  }

  closeWindow(file: IFile) {
    const compoennt = this.windowService.windowStore.getWindows().find(x => x.data === file);
    if (Component) {
      const viewContainerRef = this.contentHost.viewContainerRef;
      const index = viewContainerRef.indexOf(compoennt.component.hostView);
      viewContainerRef.remove(index);
      const newIndex = this.windowService.windowStore.getWindows().indexOf(compoennt) - 1;
      this.windowService.windowStore.getWindows().splice(newIndex + 1, 1);
      this.openWindow(this.windowService.windowStore.getWindows()[newIndex].data);
    }
  }

  loadHomeComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(HomeWindowComponent);
    const viewContainerRef = this.contentHost.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
    const window = new WindowItem(componentRef, {name: 'Home', path: 'Home', type: 0});
    this.windowService.windowStore.getWindows().push(window);
    this.windowService.windowStore.setCurrentWindow(window);
    this.windowService.WindowChanged(window);
  }

  openWindow(file: IFile) {
    if (!(this.windowService.windowStore.getCurrentWindow().data === file)) {
      const windowBaseCurrent = (this.windowService.windowStore.getCurrentWindow().component.instance as WindowBase);
      windowBaseCurrent.hidden = false;
      const viewContainerRef = this.contentHost.viewContainerRef;
      const result = this.windowService.windowStore.getWindows().find(x => x.data === file);
      if (result) {
        const windowBaseResult = result.component.instance as WindowBase;
        windowBaseResult.hidden = true;
        this.windowService.windowStore.setCurrentWindow(result);
        this.windowService.WindowChanged(result);
      } else {
        const componentFactory = this.componentFactoryResolver.resolveComponentFactory(EntityWindowComponent);
        const componentRef = viewContainerRef.createComponent(componentFactory);
        (componentRef.instance as EntityWindowComponent).file = file;
        const window = new WindowItem(componentRef, file);
        this.windowService.windowStore.addWindow(window);
        this.windowService.windowStore.setCurrentWindow(window);
        this.windowService.WindowChanged(window);
      }
    }


  }
}
