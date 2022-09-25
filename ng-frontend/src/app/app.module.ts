import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {PlanetListComponent} from './planet-list/planet-list.component';
import {PlanetService} from "./service/planet.service";
import {AppRoutingModule} from "./app.routing";
import {PathListComponent} from './path-list/path-list.component';
import {RouteListComponent} from './route-list/route-list.component';
import {PathFormComponent} from "./path-form/path-form.component";
import {RouteService} from "./service/route.service";
import {ShortestPathService} from "./service/shortest-path.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MessageService} from "./service/message.service";

@NgModule({
  declarations: [
    AppComponent,
    PlanetListComponent,
    PathListComponent,
    RouteListComponent,
    PathFormComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PlanetService, RouteService, ShortestPathService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
