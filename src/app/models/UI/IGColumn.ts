import {IView} from './IView';
import {IViewGroup} from './IViewGroup';

export class IGColumn extends IViewGroup {
  span: number;
  controls: IView[];


  constructor(span: number, controls: IView[]) {
    super();
    this.span = span;
    this.controls = controls;
  }
}
