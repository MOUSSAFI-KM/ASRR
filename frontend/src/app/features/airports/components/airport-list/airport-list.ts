import { Component, inject, OnInit } from '@angular/core';
import { AirportService } from '../../services/airport.service';
import { Airport } from '../../models/airport.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-airport-list',
  imports: [
    AsyncPipe,
    RouterLink,
    FormsModule
  ],
  templateUrl: './airport-list.html',
  styleUrl: './airport-list.css',
})
export class AirportList implements OnInit {

  private readonly airportService = inject(AirportService);
  private readonly router = inject(Router);

  private readonly airportsSubject =
    new BehaviorSubject<Airport[]>([]);

  readonly airports$: Observable<Airport[]> =
    this.airportsSubject.asObservable();

  searchTerm = '';

  ngOnInit(): void {
    this.loadAirports();
  }

  loadAirports(): void {

    this.airportService.getAllAirports().subscribe({
      next: (airports) => {
        this.airportsSubject.next(airports);
      },

      error: (error) => {
        console.error(
          'Error loading airports:',
          error
        );
      }
    });
  }

  searchAirports(): void {

    const search = this.searchTerm.trim();

    if (!search) {
      this.loadAirports();
      return;
    }

    this.airportService.searchAirports(search).subscribe({
      next: (airports) => {
        this.airportsSubject.next(airports);
      },

      error: (error) => {
        console.error(
          'Error searching airports:',
          error
        );
      }
    });
  }

  editAirport(id: string): void {

    this.router.navigate([
      '/airports',
      id,
      'edit'
    ]);
  }

  deleteAirport(id: string): void {

    const confirmed = confirm(
      'Are you sure you want to delete this airport?'
    );

    if (!confirmed) {
      return;
    }

    this.airportService.deleteAirport(id).subscribe({
      next: () => {

        console.log(
          'Airport deleted successfully'
        );

        this.loadAirports();
      },

      error: (error) => {
        console.error(
          'Error deleting airport:',
          error
        );
      }
    });
  }
}