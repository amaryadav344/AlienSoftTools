import {Component, DoCheck, EventEmitter, Input, IterableDiffers, OnInit, Output} from '@angular/core';
import {ISymbol} from '../../models/Enitity/ISymbol';
import {R} from '../../common/R';

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
  @Output() Complete: EventEmitter<ISymbol> = new EventEmitter<ISymbol>();
  @Input()
  suggestions: ISymbol[] = [];
  showAutoComplete: boolean;
  Loading: boolean;
  value: ISymbol[] = [];
  selections: ISymbol[] = [];
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
        name: value2,
        type: R.SymbolTypes.TYPE_VARIBLE,
        entityName: ''
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
          if (!(this.selections[index].name === selection)) {
            this.selections.splice(index, this.selections.length - index);
          }
        });
      }
    } else {
      this.selections = [];
    }

  }

  ngDoCheck() {
    if (this.iterableDiffer.diff(this.suggestions)) {
      this.Loading = false;
      this.showAutoComplete = true;
    }
  }

  onSelectSymbol(symbol: ISymbol) {
    this.showAutoComplete = false;
    this.selections.push(symbol);
    this.ChangeText();
    this.Complete.emit(symbol);
  }

  ChangeText() {
    let text = '';
    this.selections.forEach((value2, index) => {
      if (index !== 0) {
        text = text + this.separator + value2.name;
      } else {
        text = value2.name;
      }

    });
    this.text = text;
    this.textChange.emit(this.text);
  }

}
