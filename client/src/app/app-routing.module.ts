import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SongListComponent} from './song-list/song-list.component';
import {CityFormComponent} from './city-form/city-form.component';

const routes: Routes = [
  { path: 'users/:city', component: SongListComponent },
  { path: 'song/coords/:lat/:lon', component: SongListComponent },
  {path: 'test', component: CityFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
