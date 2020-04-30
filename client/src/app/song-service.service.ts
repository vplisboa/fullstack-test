import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Song} from './song';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SongServiceService {

  private readonly usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/?city=';
  }

  public findAll(param: string): Observable<Song[]> {
    return this.http.get<Song[]>(this.usersUrl + param);
  }
  public findByCoords(lat: string, lon: string): Observable<Song[]> {
    return this.http.get<Song[]>('http://localhost:8080/coords?lat=' + lat + '&lon=' + lon);
  }
}
