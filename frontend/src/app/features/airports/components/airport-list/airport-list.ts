import { Component, inject, OnInit } from '@angular/core';
import { AirportService } from '../../services/airport.service';
import { Airport } from '../../models/airport.model';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-airport-list',
  imports: [AsyncPipe,RouterLink],
  templateUrl: './airport-list.html',
  styleUrl: './airport-list.css',
})
export class AirportList implements OnInit{

  private readonly airportService =inject(AirportService)

  readonly airports$: Observable<Airport[]> =
    this.airportService.getAllAirports();

  ngOnInit(): void {
    //this.loadAirports();
  }

  // private loadAirports(): void{

  //   this.airportService.getAllAirports().subscribe({
  //     next: (airports)=>{
  //       this.airports = airports;

  //     },
  //     error: (error)=> {
  //       console.error('Error while loading airports : ', error);
  //     }
  //   });

  // }

}
