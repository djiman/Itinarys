import { Component, OnInit } from '@angular/core';
import { TrajetService } from '../../services/trajet.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import { Trajet } from '../../models/trajet.model';

@Component({
  selector: 'app-root',
  templateUrl: './trajettable.component.html',
  styleUrls: ['./trajettable.component.css']
})
export class TrajettableComponent implements OnInit {

  dataSource = new TrajetDataSource(this.trajetService);
  displayedColumns = ['id', 'arret', 'prochainArret'];
  constructor(private trajetService: TrajetService) { }

  ngOnInit() {
  }

}

export class TrajetDataSource extends DataSource<any> {
  constructor(private trajetService: TrajetService) {
    super();
  }
  connect(): Observable<Trajet[]> {
    return this.trajetService.getTrajet();
  }
  disconnect() {}
}
