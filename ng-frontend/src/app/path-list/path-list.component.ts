import { Component, OnInit } from '@angular/core';
import {Result} from "../model/result";
import {ShortestPathService} from "../service/shortest-path.service";
import {Observable, Subscription} from "rxjs";
import {MessageService} from "../service/message.service";
import {ShortestPathRequest} from "../model/requests/shortest-path-request";

@Component({
  selector: 'app-path-list',
  templateUrl: './path-list.component.html',
  styleUrls: ['./path-list.component.css']
})
export class PathListComponent implements OnInit {

  message: any;
  result: Result;
  request: ShortestPathRequest;

  constructor(private shortestPathService: ShortestPathService,
              private messageService: MessageService) {
    this.message = this.messageService.getMessage();
  }

  ngOnInit() {
    this.request = this.message;

    this.shortestPathService.findShortestPath(this.request.origin, this.request.destination).subscribe(data => {
      this.result = data;
    });
  }

  ngOnDestroy() {
    this.messageService.clearMessage();
  }

}
