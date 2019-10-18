import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-code-editor',
  templateUrl: './code-editor.component.html',
  styleUrls: ['./code-editor.component.css']
})
export class CodeEditorComponent implements OnInit {
  @Input() language: string;
  editorOptions = {};
  @Input() code: string;
  @Output() CodeChanged: EventEmitter<string> = new EventEmitter<string>();


  constructor() {
  }

  ngOnInit() {
    this.editorOptions = {theme: 'vs-dark', language: this.language};
  }

  handleCodeChanged(event: string) {
    this.CodeChanged.emit(event);
  }


}
