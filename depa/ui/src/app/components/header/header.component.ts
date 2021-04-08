import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbPopover } from '@ng-bootstrap/ng-bootstrap/popover/popover';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  
  configInfo: any;
  searchInit  = false;
  userOpen    = false;
  display = 'none';
  webSocketEndPoint: string = 'ws';
  topic: string = "/user/queue/notify";
  stompClient: any;
  popUpMessage: string;
  notificationTooltip:boolean;
  messageReceived:string;
  receivedMessage:string;
  constructor(private dataService: DataService,
    private router: Router) { 
    }

  ngOnInit() {
    this.dataService.currentConfigInfoAll.subscribe(configInfo => {
      this.configInfo = configInfo;
    });
    this._connect();
  }

  //INIT search
  search() {
    this.searchInit = true;
  }

  openModal() {
    this.display = 'block';
   
  }

  onCloseHandled() {
   
    this.display = 'none';

  }

  nav() {
    this.router.navigate(["/scenarios"]);

  }
  openPop(popover : NgbPopover): void {
   this.messageReceived="";
   popover.close();
   
  }
  _connect() {
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
        that.stompClient.subscribe(that.topic, function (sdkEvent) {
          that.receivedMessage = JSON.parse(sdkEvent.body).content;
          that.messageReceived="New notification";
        });
    }, this.errorCallBack);
};

_disconnect() {
    if (this.stompClient !== null) {
        this.stompClient.disconnect();
    }
    console.log("Disconnected");
}

// on error, schedule a reconnection attempt
errorCallBack(error) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
        this._connect();
    }, 5000);
}

/**
* Send message to sever via web socket
* @param {*} message 
*/
_send(message) {
    console.log("calling logout api via web socket");
    this.stompClient.send("/app/hello", {}, JSON.stringify(message));
}

onMessageReceived(message) {
    console.log("Message Recieved from Server :: " + message);
}

}