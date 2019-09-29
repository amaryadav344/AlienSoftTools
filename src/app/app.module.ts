import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {SideBarComponent} from './components/side-bar/side-bar.component';

import {FooterComponent} from './components/footer/footer.component';
import {AlertModule} from 'ngx-bootstrap';
import {HeaderComponent} from './components/header/header.component';
import {SamplesComponent} from './components/samples/samples.component';
import {EntityWindowComponent} from './components/entity-window/entity-window.component';
import {TabModule} from 'angular-tabs-component';
import {
  AutoCompleteModule,
  CheckboxModule, ContextMenuModule, DropdownModule, InputTextareaModule, OrderListModule, PaginatorModule,
  RadioButtonModule,
  ScrollPanelModule, SplitButtonModule,
  TabViewModule
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
import {QueryInfoDialogComponent} from './components/dialogs/query-info-diaglog/query-info-diaglog.component';
import {MenuModule} from 'primeng/menu';


@NgModule({
  declarations: [
    AppComponent,
    MainAppComponent,
    SideBarComponent,
    FooterComponent,
    HeaderComponent,
    SamplesComponent,
    EntityWindowComponent,
    ColumnInfoDialogComponent,
    ObjectInfoDialogComponent,
    CollectionInfoDialogComponent,
    QueryInfoDialogComponent
  ],
  imports: [
    RouterModule.forRoot([]),
    BrowserAnimationsModule,
    BrowserModule,
    AlertModule.forRoot(),
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
    MenuModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    ColumnInfoDialogComponent,
    ObjectInfoDialogComponent,
    CollectionInfoDialogComponent,
    QueryInfoDialogComponent,
  ]
})
export class AppModule {
}
