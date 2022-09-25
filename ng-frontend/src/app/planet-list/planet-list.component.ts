import { Component, OnInit } from '@angular/core';
import {Planet} from "../model/planet";
import {PlanetService} from "../service/planet.service";

@Component({
  selector: 'app-planet-list',
  templateUrl: './planet-list.component.html',
  styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent implements OnInit {

  planets: Array<Planet>;

  constructor(private planetService: PlanetService) { }

  ngOnInit() {
    this.planetService.findAll().subscribe(data => {
      this.planets = data;
    });
  }

}
