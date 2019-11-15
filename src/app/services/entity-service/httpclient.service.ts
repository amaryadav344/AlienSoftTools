import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {R} from '../../common/R';
import {IFile} from '../../models/Enitity/IFile';
import {IColumn} from '../../models/Enitity/IColumn';
import {IXMLBase} from '../../models/IXMLBase';
import {IDBConnectionInfo} from '../../models/Enitity/IDBConnectionInfo';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(public httpClient: HttpClient) {
  }

  public getXMLFromJS(entity: IXMLBase) {
    return this.httpClient.post(R.SERVER_URLS.JS_TO_XML, entity, {responseType: 'text'});
  }

  public getJSFromXML(xml: string) {
    return this.httpClient.post<IXMLBase>(R.SERVER_URLS.XML_TO_JS, xml, {responseType: 'json'});
  }

  public saveXML(entity: IXMLBase, Path: string) {
    let params = new HttpParams();
    params = params.append('path', Path);
    return this.httpClient.post(R.SERVER_URLS.SAVE_XML, entity, {params, responseType: 'text'});
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

  public getDBConnectionInfo() {
    return this.httpClient.post<IDBConnectionInfo>(R.SERVER_URLS.GET_DB_CONNECTION_INFO, ' ', {responseType: 'json'});
  }

  public getSymbols(file: IFile, query: string) {
    let params = new HttpParams();
    params = params.append('query', query);
    return this.httpClient.post<string[]>(R.SERVER_URLS.GET_SYMBOLS, file, {params, responseType: 'json'});
  }

  public LoadProject() {
    return this.httpClient.get(R.SERVER_URLS.LOAD_PROJECT);
  }
}
