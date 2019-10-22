import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ContentDirective} from '../../directives/content.directive';
import {WindowService} from '../../services/window/window.service';
import {EntityWindowComponent} from '../entity/entity-window.component';
import {IFile} from '../../models/IFile';
import {HomeWindowComponent} from '../home-window/home-window.component';
import {WindowItem} from '../../common/window-Item';

@Component({
  selector: 'app-window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.css']
})
export class WindowComponent implements OnInit, AfterViewInit {
  windows: WindowItem[] = [];
  currentWindow: WindowItem;
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
    this.windowService.CloseWindow$.subscribe((file) => {
      this.closeWindow(file);
    });
  }

  ngAfterViewInit() {

  }

  closeWindow(file: IFile) {
    const compoennt = this.windows.find(x => x.data === file);
    if (Component) {
      const viewContainerRef = this.contentHost.viewContainerRef;
      const index = viewContainerRef.indexOf(compoennt.component.hostView);
      viewContainerRef.remove(index);
      const newIndex = this.windows.indexOf(compoennt) - 1;
      this.windows.splice(newIndex + 1, 1);
      this.openWindow(this.windows[newIndex].data);
    }
  }

  loadHomeComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(HomeWindowComponent);
    const viewContainerRef = this.contentHost.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
    const window = new WindowItem(componentRef, '');
    this.windows.push(window);
    this.currentWindow = window;
  }

  openWindow(file: IFile) {
    if (!(this.currentWindow.data === file)) {
      if (this.currentWindow.component.instance instanceof EntityWindowComponent) {
        (this.currentWindow.component.instance as EntityWindowComponent).isHidden = true;
      } else {
        (this.currentWindow.component.instance as HomeWindowComponent).isHidden = true;
      }
      const viewContainerRef = this.contentHost.viewContainerRef;
      const result = this.windows.find(x => x.data === file);
      if (result) {
        if (result.component.instance instanceof EntityWindowComponent) {
          (result.component.instance as EntityWindowComponent).isHidden = false;
        } else {
          (result.component.instance as HomeWindowComponent).isHidden = false;
        }
        this.currentWindow = result;
      } else {
        const componentFactory = this.componentFactoryResolver.resolveComponentFactory(EntityWindowComponent);
        const componentRef = viewContainerRef.createComponent(componentFactory);
        (componentRef.instance as EntityWindowComponent).file = file;
        const window = new WindowItem(componentRef, file);
        this.windows.push(window);
        this.currentWindow = window;
      }
    }


  }
}
