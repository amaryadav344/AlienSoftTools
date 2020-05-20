import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {FooterComponent} from './components/footer/footer.component';
import {AlertModule} from 'ngx-bootstrap';
import {HeaderComponent} from './components/header/header.component';
import {EntityWindowComponent} from './components/entity/entity-window.component';
import {TabModule} from 'angular-tabs-component';
import {
  AccordionModule,
  AutoCompleteModule,
  BreadcrumbModule,
  CheckboxModule,
  ColorPickerModule,
  ConfirmDialogModule,
  ContextMenuModule,
  DragDropModule,
  DropdownModule,
  InputTextareaModule,
  OrderListModule,
  OverlayPanelModule,
  PaginatorModule,
  PasswordModule,
  ProgressSpinnerModule,
  RadioButtonModule,
  ScrollPanelModule,
  SpinnerModule,
  SplitButtonModule,
  TabMenuModule,
  TabViewModule,
  ToolbarModule,
  TooltipModule,
} from 'primeng/primeng';
import {TableModule} from 'primeng/table';
import {DataViewModule} from 'primeng/dataview';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/components/inputtext/inputtext';
import {ListboxModule} from 'primeng/listbox';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PanelModule} from 'primeng/panel';
import {DynamicDialogModule} from 'primeng/dynamicdialog';
import {RouterModule} from '@angular/router';
import {MessageModule} from 'primeng/message';
import {MenuModule} from 'primeng/menu';
import {ExpressionEditorComponent} from './components/expression-editor/expression-editor.component';
import {AceEditorModule} from 'ng2-ace-editor';
import {EntityToolbarComponent} from './components/entity/entity-toolbar/entity-toolbar.component';
import {HttpClientModule} from '@angular/common/http';
import {DropDownComponent} from './components/drop-down/drop-down.component';
import {CodeEditorComponent} from './components/code-editor/code-editor.component';
import {MonacoEditorModule} from 'ngx-monaco-editor';
import {WindowComponent} from './components/window/window.component';
import {HomeWindowComponent} from './components/home-window/home-window.component';
import {ContentDirective} from './directives/content.directive';
import {NewEntityDialogComponent} from './components/dialogs/new-entity-dialog/new-entity-dialog.component';
import {EntityInfoDialogComponent} from './components/dialogs/entity-info-dialog/entity-info-dialog.component';
import {ToastModule} from 'primeng/toast';
import {UserInterfaceComponent} from './components/ui/user-interface/user-interface.component';
import {FormComponent} from './components/ui/form/form.component';
import {SideBarComponent} from './components/ui/side-bar/side-bar.component';
import {UIToolbarComponent} from './components/ui/uitoolbar/uitoolbar.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {MasterWindowComponent} from './components/master-window/master-window.component';
import {MasterLoginWindowComponent} from './components/master-window/master-login-window/master-login-window.component';
import {MasterIntitalLoadComponent} from './components/master-window/master-intital-load/master-intital-load.component';
import {CustomAutoCompleteComponent} from './components/custom-auto-complete/custom-auto-complete.component';
import {NewFrameDialogComponent} from './components/dialogs/new-frame-dialog/new-frame-dialog.component';
import {FormInfoDialogComponent} from './components/dialogs/form-info-dialog/form-info-dialog.component';
import {ChartModule} from 'primeng/chart';
import {AttributeInfoDialogComponent} from './components/dialogs/attribute-info-dialog/attribute-info-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    MainAppComponent,
    SideBarComponent,
    FooterComponent,
    HeaderComponent,
    EntityWindowComponent,
    ExpressionEditorComponent,
    EntityToolbarComponent,
    DropDownComponent,
    CodeEditorComponent,
    WindowComponent,
    HomeWindowComponent,
    ContentDirective,
    NewEntityDialogComponent,
    EntityInfoDialogComponent,
    UserInterfaceComponent,
    FormComponent,
    UIToolbarComponent,
    MasterWindowComponent,
    MasterLoginWindowComponent,
    MasterIntitalLoadComponent,
    CustomAutoCompleteComponent,
    NewFrameDialogComponent,
    FormInfoDialogComponent,
    AttributeInfoDialogComponent,
  ],
  imports: [
    RouterModule.forRoot([]),
    BrowserAnimationsModule,
    BrowserModule,
    AlertModule.forRoot(),
    MonacoEditorModule.forRoot(),
    HttpClientModule,
    AngularFontAwesomeModule,
    AceEditorModule,
    TabModule,
    TabViewModule,
    TableModule,
    PasswordModule,
    PaginatorModule,
    DataViewModule,
    CheckboxModule,
    RadioButtonModule,
    ButtonModule,
    InputTextModule,
    ScrollPanelModule,
    ListboxModule,
    DropdownModule,
    InputTextareaModule,
    PanelModule,
    OrderListModule,
    AutoCompleteModule,
    DynamicDialogModule,
    SplitButtonModule,
    MessageModule,
    ContextMenuModule,
    MenuModule,
    TooltipModule,
    CheckboxModule,
    DragDropModule,
    ToolbarModule,
    TabMenuModule,
    OverlayPanelModule,
    ConfirmDialogModule,
    ToastModule,
    BreadcrumbModule,
    SpinnerModule,
    ProgressSpinnerModule,
    AccordionModule,
    ColorPickerModule,
    ChartModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    HomeWindowComponent,
    NewEntityDialogComponent,
    NewFrameDialogComponent,
    EntityWindowComponent,
    EntityInfoDialogComponent,
    FormInfoDialogComponent,
    UserInterfaceComponent,
    MasterLoginWindowComponent,
    MasterIntitalLoadComponent,
    AttributeInfoDialogComponent,
    MainAppComponent,


  ]
})
export class AppModule {
}
