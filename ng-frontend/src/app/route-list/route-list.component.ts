import {Component, OnInit} from '@angular/core';
import {Route} from "../model/route";
import {RouteService} from "../service/route.service";

@Component({
  selector: 'app-route-list',
  templateUrl: './route-list.component.html',
  styleUrls: ['./route-list.component.css']
})
export class RouteListComponent implements OnInit {

  routes: Route[];

  constructor(private routeService: RouteService) {
  }

  ngOnInit() {
    this.routeService.findAll().subscribe(data => {
      this.routes = data
    });
  }

}
