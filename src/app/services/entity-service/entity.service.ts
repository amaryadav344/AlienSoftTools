import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IEntity} from '../../models/IEntity';
import {R} from '../../common/R';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(public httpClient: HttpClient) {
  }

  public getXMLFromJS(entity: IEntity): any {
    return this.httpClient.post(R.SERVER_URLS.JS_TO_XML, entity, {responseType: 'text'});
  }

  public getJSFromXML(xml: string) {
    return this.httpClient.post<IEntity>(R.SERVER_URLS.XML_TO_JS, xml, {responseType: 'json'});
  }
}
