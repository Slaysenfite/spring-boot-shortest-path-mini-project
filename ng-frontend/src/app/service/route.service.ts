import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Route} from "../model/route";

@Injectable({
  providedIn: 'root'
})
export class RouteService {
  private readonly routesUrl: string;

  constructor(private http: HttpClient) {
    this.routesUrl = 'http://localhost:8080/routes'
  }

  public findAll(): Observable<Route[]> {
    return this.http.get<Route[]>(this.routesUrl);
  }
}
