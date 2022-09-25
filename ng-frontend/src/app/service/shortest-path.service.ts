import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Result} from "../model/result";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShortestPathService {

  private readonly shortestPathUrl: string;

  constructor(private http: HttpClient) {
    this.shortestPathUrl = 'http://localhost:8080/shortestPath'
  }

  public findShortestPath(origin: string, destination: string): Observable<Result> {
    let params = new HttpParams()
      .set("originPlanetName", origin)
      .set("destinationPlanetName", destination);

    return this.http.get<Result>(this.shortestPathUrl, {params: params})
  }
}
