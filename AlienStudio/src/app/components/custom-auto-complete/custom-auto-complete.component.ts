import {Component, DoCheck, EventEmitter, Input, IterableDiffers, OnInit, Output} from '@angular/core';
import {R} from '../../common/R';
import {IAttribute} from '../../models/Enitity/IAttribute';

@Component({
  selector: 'app-custom-auto-complete',
  templateUrl: './custom-auto-complete.component.html',
  styleUrls: ['./custom-auto-complete.component.css']
})
export class CustomAutoCompleteComponent implements OnInit, DoCheck {
  @Input()
  text: string;
  @Output() textChange: EventEmitter<string> = new EventEmitter<string>();
  @Input()
  separator = '.';
  @Output() Change: EventEmitter<string> = new EventEmitter<string>();
  @Output() Complete: EventEmitter<IAttribute> = new EventEmitter<IAttribute>();
  @Input()
  suggestions: IAttribute[] = [];
  showAutoComplete: boolean;
  Loading: boolean;
  value: IAttribute[] = [];
  selections: IAttribute[] = [];
  iterableDiffer: any;


  constructor(private iterableDiffers: IterableDiffers) {
    this.iterableDiffer = this.iterableDiffers.find([]).create(null);

  }

  ngOnInit() {
    if (!this.text) {
      this.text = '';
    }
    const selections = this.text.split(this.separator);
    if (!(selections === null)) {
      selections.forEach(value2 => this.selections.push({
        name: '',
        type: R.AttributeTypes.PROPERTY,
        entity: '',
        objectField: value2,
        dataType: '',
        isPrimaryKey: false,
      }));
    }
  }

  onChange() {
    this.Loading = true;
    this.Change.emit(this.text);
    if (this.text !== '') {
      if (!(this.selections === null)) {
        const selections = this.text.split(this.separator);
        selections.forEach((selection, index) => {
          if (this.selections[index]) {
            if (!(this.selections[index].objectField === selection)) {
              this.selections.splice(index, this.selections.length - index);
            }
          } else {

          }
        });
      }
    } else {
      this.selections = [];
    }

  }

  onTextChange(ChangedText) {
    this.text = ChangedText;
  }

  ngDoCheck() {
    if (this.iterableDiffer.diff(this.suggestions)) {
      this.Loading = false;
      this.showAutoComplete = true;
    }
  }

  onSelectAttribute(attribute: IAttribute) {
    this.showAutoComplete = false;
    this.selections.push(attribute);
    this.ChangeText();
    this.Complete.emit(attribute);
  }

  ChangeText() {
    let text = '';
    this.selections.forEach((value2, index) => {
      if (index !== 0) {
        text = text + this.separator + value2.objectField;
      } else {
        text = value2.objectField;
      }

    });
    this.text = text;
    this.textChange.emit(this.text);
  }

  closeAllLists(elmnt) {

  }

}
