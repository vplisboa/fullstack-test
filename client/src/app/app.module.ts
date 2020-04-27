import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { SongListComponent} from './song-list/song-list.component';
import {SongServiceService} from './song-service.service';
import { CityFormComponent } from './city-form/city-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SongListComponent,
    CityFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [SongServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
