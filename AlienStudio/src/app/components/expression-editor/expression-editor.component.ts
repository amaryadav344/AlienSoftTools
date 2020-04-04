import {Component, Input, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-expression-editor',
  templateUrl: './expression-editor.component.html',
  styleUrls: ['./expression-editor.component.css']
})
export class ExpressionEditorComponent implements OnInit {
  text = 'function x() {\nconsole.log("Hello world!");\n}';
  @ViewChild('editor', {static: false}) editor;
  @Input() expression: string;

  constructor() {
  }

  ngOnInit() {
    /* // this.editor.setTheme('eclipse');
      this.editor.getEditor().setOptions({
        enableBasicAutocompletion: true
      });
      this.editor.getEditor().commands.addCommand({
        name: 'showOtherCompletions',
        bindKey: 'Ctrl-.',
        exec(editor) {
        }
      });*/
  }

}
