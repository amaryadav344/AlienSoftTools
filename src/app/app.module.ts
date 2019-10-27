import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {SideBarComponent} from './components/side-bar/side-bar.component';

import {FooterComponent} from './components/footer/footer.component';
import {AlertModule} from 'ngx-bootstrap';
import {HeaderComponent} from './components/header/header.component';
import {EntityWindowComponent} from './components/entity/entity-window.component';
import {TabModule} from 'angular-tabs-component';
import {
  AutoCompleteModule,
  CheckboxModule,
  ConfirmDialogModule,
  ContextMenuModule,
  DragDropModule,
  DropdownModule,
  InputTextareaModule,
  OrderListModule,
  OverlayPanelModule,
  PaginatorModule,
  RadioButtonModule,
  ScrollPanelModule,
  SplitButtonModule,
  TabMenuModule,
  TabViewModule,
  ToolbarModule,
  TooltipModule
} from 'primeng/primeng';
import {TableModule} from 'primeng/table';
import {DataViewModule} from 'primeng/dataview';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/components/inputtext/inputtext';
import {ListboxModule} from 'primeng/listbox';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PanelModule} from 'primeng/panel';
import {DynamicDialogModule} from 'primeng/dynamicdialog';
import {ColumnInfoDialogComponent} from './components/dialogs/column-info-dialog/column-info-dialog.component';
import {RouterModule} from '@angular/router';
import {ObjectInfoDialogComponent} from './components/dialogs/object-info-dialog/object-info-dialog.component';
import {CollectionInfoDialogComponent} from './components/dialogs/collection-info-dialog/collection-info-dialog.component';
import {MessageModule} from 'primeng/message';
import {QueryInfoDialogComponent} from './components/dialogs/query-info-diaglog/query-info-dialog.component';
import {MenuModule} from 'primeng/menu';
import {ExpressionEditorComponent} from './components/expression-editor/expression-editor.component';
import {AceEditorModule} from 'ng2-ace-editor';
import {ValidationTabComponent} from './components/entity/validation-tab/validation-tab.component';
import {RuleInfoDialogComponent} from './components/dialogs/rule-info-dialog/rule-info-dialog.component';
import {GroupInfoDialogComponent} from './components/dialogs/group-info-dialog/group-info-dialog.component';
import {BusinessObjectTabComponent} from './components/entity/business-object-tab/business-object-tab.component';
import {CustomMethodInfoDialogComponent} from './components/dialogs/custom-method-info-dialog/custom-method-info-dialog.component';
import {LoadParamterInfoDialogComponent} from './components/dialogs/load-paramter-info-dialog/load-paramter-info-dialog.component';
import {ObjectMethodInfoDialogComponent} from './components/dialogs/object-method-info-dialog/object-method-info-dialog.component';
import {AttributesTabComponent} from './components/entity/attributes-tab/attributes-tab.component';
import {ColumnsTabComponent} from './components/entity/attributes-tab/columns-tab/columns-tab.component';
import {ObjectsTabComponent} from './components/entity/attributes-tab/objects-tab/objects-tab.component';
import {CollectionsTabComponent} from './components/entity/attributes-tab/collections-tab/collections-tab.component';
import {QueryTabComponent} from './components/entity/query-tab/query-tab.component';
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


@NgModule({
  declarations: [
    AppComponent,
    MainAppComponent,
    SideBarComponent,
    FooterComponent,
    HeaderComponent,
    EntityWindowComponent,
    ColumnInfoDialogComponent,
    ObjectInfoDialogComponent,
    CollectionInfoDialogComponent,
    QueryInfoDialogComponent,
    ExpressionEditorComponent,
    ValidationTabComponent,
    RuleInfoDialogComponent,
    GroupInfoDialogComponent,
    BusinessObjectTabComponent,
    CustomMethodInfoDialogComponent,
    LoadParamterInfoDialogComponent,
    ObjectMethodInfoDialogComponent,
    AttributesTabComponent,
    ColumnsTabComponent,
    ObjectsTabComponent,
    CollectionsTabComponent,
    QueryTabComponent,
    EntityToolbarComponent,
    DropDownComponent,
    CodeEditorComponent,
    WindowComponent,
    HomeWindowComponent,
    ContentDirective,
    NewEntityDialogComponent,
    EntityInfoDialogComponent,
  ],
  imports: [
    RouterModule.forRoot([]),
    BrowserAnimationsModule,
    BrowserModule,
    AlertModule.forRoot(),
    MonacoEditorModule.forRoot(),
    HttpClientModule,
    AceEditorModule,
    TabModule,
    TabViewModule,
    TableModule,
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
    ConfirmDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    ColumnInfoDialogComponent,
    ObjectInfoDialogComponent,
    CollectionInfoDialogComponent,
    QueryInfoDialogComponent,
    RuleInfoDialogComponent,
    GroupInfoDialogComponent,
    CustomMethodInfoDialogComponent,
    LoadParamterInfoDialogComponent,
    ObjectMethodInfoDialogComponent,
    HomeWindowComponent,
    NewEntityDialogComponent,
    EntityWindowComponent,
    EntityInfoDialogComponent

  ]
})
export class AppModule {
}
