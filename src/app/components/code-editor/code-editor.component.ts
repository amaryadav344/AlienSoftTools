import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-code-editor',
  templateUrl: './code-editor.component.html',
  styleUrls: ['./code-editor.component.css']
})
export class CodeEditorComponent implements OnInit {
  editorOptions = {theme: 'vs-dark', language: 'xml'};
  @Input() code: string;

  constructor() {
  }

  ngOnInit() {

  }


}
