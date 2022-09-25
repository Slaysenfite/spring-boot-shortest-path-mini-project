import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PlanetListComponent} from "./planet-list/planet-list.component";
import {RouteListComponent} from "./route-list/route-list.component";
import {PathFormComponent} from "./path-form/path-form.component";
import {PathListComponent} from "./path-list/path-list.component";

const routes: Routes = [
  {path: 'planets', component: PlanetListComponent},
  {path: 'routes', component: RouteListComponent},
  {path: 'findPath', component: PathFormComponent},
  {path: 'shortestPath', component: PathListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
