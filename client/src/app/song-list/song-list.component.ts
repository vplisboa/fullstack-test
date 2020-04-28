import { Component, OnInit } from '@angular/core';
import {Song} from '../song';
import {SongServiceService} from '../song-service.service';
import {ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-song-list',
  templateUrl: './song-list.component.html',
  styleUrls: ['./song-list.component.css']
})
export class SongListComponent implements OnInit {

  constructor(private songService: SongServiceService, private route: ActivatedRoute) { }
  city: string;
  lat: string;
  lon: string;
  songs: Song[];

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if (params.has('city')) {
        this.cityByName(params);
      } else {
        this.cityByCoords(params);
      }
    });


  }

  cityByName(params: ParamMap) {
    this.city = params.get('city')
    this.songService.findAll(this.city).subscribe(data => {
      this.songs = data;
    });
  }

  cityByCoords(params: ParamMap) {
    this.lat = params.get('lat')
    this.lon = params.get('lon')
    this.songService.findByCoords(this.lat, this.lon).subscribe(data => {
      this.songs = data;
    });
  }
}
