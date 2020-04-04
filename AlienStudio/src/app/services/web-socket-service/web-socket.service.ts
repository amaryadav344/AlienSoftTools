import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import {Client} from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  webSocketEndPoint = 'http://localhost:8080/ws';
  topic = '/topic/greetings';
  stompClient: Client;
  connected = false;

  constructor() {
    this._connect();

  }

  _connect() {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, frame => {
      this.stompClient.subscribe(this.topic, sdkEvent => {
        this.onMessageReceived(sdkEvent);
      });
      this.stompClient.subscribe('/topic', sdkEvent => {
        this.onMessageReceived(sdkEvent);
      });
      this.connected = true;

    }, (error2 => {
      this.connected = false;
    }));
  }

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect(() => {
      });
    }
    console.log('Disconnected');
  }

  _send(message) {
    console.log('calling logout api via web socket');
    this.stompClient.send('/app/common', {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
    console.log('Message Recieved from Server :: ' + message);
    /*
        this.appComponent.handleMessage(JSON.stringify(message.body));
    */
  }
}
