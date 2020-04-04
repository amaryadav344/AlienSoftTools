export class DragDropHelper {
  private static mDragDropHelper: DragDropHelper;
  Control: string;

  private constructor() {
  }

  static getInstance() {
    if (this.mDragDropHelper == null) {
      this.mDragDropHelper = new DragDropHelper();
    }
    return this.mDragDropHelper;
  }

  getControl() {
    return this.Control;
  }

  setControl(Control: string) {
    this.Control = Control;
  }


}
