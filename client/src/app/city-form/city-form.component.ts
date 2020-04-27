import { Component, OnInit } from '@angular/core';
import { City} from '../city';
import {SongServiceService} from '../song-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-city-form',
  templateUrl: './city-form.component.html',
  styleUrls: ['./city-form.component.css']
})
export class CityFormComponent implements OnInit {
  city: City;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private songService: SongServiceService) {
    this.city = new City();
  }

  onSubmit() {
    console.log(this.city)
    if (this.city.name != null) {
      this.songService.findAll(this.city.name);
      this.router.navigate(['/users/' + this.city.name]);
    }
    if ( this.city.lat != null && this.city.lon != null) {
      this.songService.findByCoords(this.city.lat, this.city.lon);
      this.router.navigate(['song/coords/' + this.city.lat + '/' + this.city.lon]);
    }
  }

  ngOnInit() {
  }

}
