import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {IEntity} from '../../models/IEntity';
import {R} from '../../common/R';
import {IFile} from "../../models/IFile";
import {IColumn} from "../../models/IColumn";

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(public httpClient: HttpClient) {
  }

  public getXMLFromJS(entity: IEntity) {
    return this.httpClient.post(R.SERVER_URLS.JS_TO_XML, entity, {responseType: 'text'});
  }

  public getJSFromXML(xml: string) {
    return this.httpClient.post<IEntity>(R.SERVER_URLS.XML_TO_JS, xml, {responseType: 'json'});
  }

  public getFile(file: IFile) {
    return this.httpClient.post<IEntity>(R.SERVER_URLS.GET_FILE, file, {responseType: 'json'});
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

  public createNewXml(entity: IEntity, Path: string) {
    let params = new HttpParams();
    params = params.append('path', Path);
    return this.httpClient.post(R.SERVER_URLS.CREATE_NEW_XML, entity, {params: params, responseType: 'json'});
  }
}
