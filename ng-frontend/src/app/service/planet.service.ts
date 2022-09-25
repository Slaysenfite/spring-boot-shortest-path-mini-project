import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Planet} from "../model/planet";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PlanetService {
  private readonly planetsUrl: string;

  constructor(private http: HttpClient) {
    this.planetsUrl = 'http://localhost:8080/planets'
  }

  public findAll(): Observable<Planet[]> {
    return this.http.get<Planet[]>(this.planetsUrl);
  }
}
