import {WindowItem} from './window-Item';

export class WindowStore {
  private static instance: WindowStore;
  private windows: WindowItem[] = [];
  private currentWindow: WindowItem;


  private constructor() {
  }

  static getInstance() {
    if (!WindowStore.instance) {
      WindowStore.instance = new WindowStore();
    }
    return WindowStore.instance;
  }

  setCurrentWindow(window: WindowItem) {
    this.currentWindow = window;
  }

  getCurrentWindow(): WindowItem {
    return this.currentWindow;
  }

  addWindow(window: WindowItem) {
    this.windows.push(window);

  }

  getWindows(): WindowItem[] {
    return this.windows;
  }

  getWindow(index: number): WindowItem {
    return this.windows[index];
  }

  removeWindow(window: WindowItem) {
    const index = this.windows.indexOf(window);
    this.windows.splice(index, 1);
  }

  removeWindowAtIndex(index: number) {
    this.windows.splice(index, 1);
  }

}
