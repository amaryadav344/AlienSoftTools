import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {R} from '../../common/R';
import {IFile} from '../../models/Enitity/IFile';
import {IColumn} from '../../models/Enitity/IColumn';
import {IXMLBase} from '../../models/IXMLBase';
import {IDBConnectionInfo} from '../../models/Enitity/IDBConnectionInfo';
import {ISymbol} from '../../models/Enitity/ISymbol';
import {IObjectMethod} from '../../models/Enitity/IObjectMethod';
import {NavigationParameter} from "../../models/UI/NavigationParameter";

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(public httpClient: HttpClient) {
  }

  public getXMLFromJS(xml: IXMLBase) {
    return this.httpClient.post(R.SERVER_URLS.JS_TO_XML, xml, {responseType: 'text'});
  }

  public getJSFromXML(xml: string) {
    return this.httpClient.post<IXMLBase>(R.SERVER_URLS.XML_TO_JS, xml, {responseType: 'json'});
  }

  public saveXML(xml: IXMLBase, Path: string) {
    let params = new HttpParams();
    params = params.append('path', Path);
    return this.httpClient.post(R.SERVER_URLS.SAVE_XML, xml, {params, responseType: 'text'});
  }

  public getFile(file: IFile) {
    return this.httpClient.post<IXMLBase>(R.SERVER_URLS.GET_FILE, file, {responseType: 'json'});
  }

  public getMatchingFolders(query: string) {
    return this.httpClient.post<string[]>(R.SERVER_URLS.GET_MATCHING_FOLDERS, query, {responseType: 'json'});
  }

  public getMatchingTables(query: string) {
    return this.httpClient.post<string[]>(R.SERVER_URLS.GET_MATCHING_TABLES, query, {responseType: 'json'});
  }

  public getColumns(table: string) {
    return this.httpClient.post<IColumn[]>(R.SERVER_URLS.GET_COLUMNS, table, {responseType: 'json'});
  }

  public createNewXml(entity: IXMLBase, Path: string, createModel: boolean) {
    let params = new HttpParams();
    params = params.append('path', Path);
    params = params.append('createModel', createModel.toString());
    return this.httpClient.post(R.SERVER_URLS.CREATE_NEW_XML, entity, {params, responseType: 'json'});
  }

  public getFiles() {
    return this.httpClient.post<IFile[]>(R.SERVER_URLS.GET_FILES, ' ', {responseType: 'json'});
  }

  public getForms(query: string) {
    return this.httpClient.post<string[]>(R.SERVER_URLS.GET_FORMS, query, {
      responseType: 'json'
    });
  }

  public getNavigationParameters(form: string) {
    return this.httpClient.post<NavigationParameter[]>(R.SERVER_URLS.GET_NAVIGATION_PARAMETER_BY_FORM, form, {
      responseType: 'json'
    });
  }


  public getDBConnectionInfo() {
    return this.httpClient.post<IDBConnectionInfo>(R.SERVER_URLS.GET_DB_CONNECTION_INFO, ' ', {responseType: 'json'});
  }

  public getSymbols(file: IFile, query: string, SymbolType: number) {
    let params = new HttpParams();
    params = params.append('query', query);
    params = params.append('type', SymbolType.toString());
    return this.httpClient.post<ISymbol[]>(R.SERVER_URLS.GET_SYMBOLS, file, {params, responseType: 'json'});
  }

  public getObjectMethods(file: IFile, query: string) {
    let params = new HttpParams();
    params = params.append('query', query);
    return this.httpClient.post<IObjectMethod[]>(R.SERVER_URLS.GET_OBJECT_METHODS, file, {
      params,
      responseType: 'json'
    });
  }

  public listEntities(query: string) {
    return this.httpClient.post<string[]>(R.SERVER_URLS.LIST_ENTITIES, query, {
      responseType: 'json'
    });
  }

  public LoadProject() {
    return this.httpClient.get(R.SERVER_URLS.LOAD_PROJECT);
  }

  public getEntityFields(entity: string, query: string) {
    let params = new HttpParams();
    params = params.append('query', query);
    return this.httpClient.post<ISymbol[]>(R.SERVER_URLS.GET_ENTITY_FIELDS, entity, {params, responseType: 'json'});
  }
}
