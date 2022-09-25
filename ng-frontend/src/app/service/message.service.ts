import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private subject: Subject<any>;

  constructor() {
    this.subject = new Subject<any>();
  }

  sendMessage(message: any) {
    this.subject = message;
  }

  clearMessage() {
    this.subject = new Subject<any>();
  }

  getMessage(): any {
    return this.subject;
  }
}
