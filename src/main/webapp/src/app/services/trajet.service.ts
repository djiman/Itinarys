import { Injectable }   from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable }   from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Trajet } from '../models/trajet.model';

@Injectable()
export class TrajetService {

   private serviceUrl = 'http://localhost:8080/trajet';
  
  constructor(private http: HttpClient) { }
  
  getTrajet(): Observable<Trajet[]> {
    return this.http.get<Trajet[]>(this.serviceUrl);
  }

}
