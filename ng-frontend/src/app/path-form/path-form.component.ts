import {Component} from '@angular/core';
import {ShortestPathRequest} from "../model/requests/shortest-path-request";
import {ActivatedRoute, Router} from "@angular/router";
import {Planet} from "../model/planet";
import {PlanetService} from "../service/planet.service";
import {MessageService} from "../service/message.service";

@Component({
  selector: 'app-path-form',
  templateUrl: './path-form.component.html',
  styleUrls: ['./path-form.component.css']
})
export class PathFormComponent {

  request: ShortestPathRequest;

  origin: string = 'Earth';
  destination: string = 'Pluto';

  planets: Array<Planet>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private planetService: PlanetService,
              private messageService: MessageService
  ) {
    this.request = new ShortestPathRequest(this.origin, this.destination);

    planetService.findAll().subscribe(data => {
      this.planets = data;
    });

  }

  onSubmit() {
    this.request = new ShortestPathRequest(this.origin, this.destination);

    this.messageService.sendMessage(this.request);
    this.gotoPathList();
  }

  gotoPathList() {
    this.router.navigate(['/shortestPath']);
  }

}
