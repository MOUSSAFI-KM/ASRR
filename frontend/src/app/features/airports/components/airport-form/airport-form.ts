import { Component, Inject, inject } from '@angular/core';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';

import { AirportService } from '../../services/airport.service';
import { AirportRequest } from '../../models/airport-request.model';

@Component({
  selector: 'app-airport-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './airport-form.html',
  styleUrl: './airport-form.css'
})
export class AirportForm {

  private readonly formBuilder = inject(FormBuilder);
  private readonly airportService = inject(AirportService);
  private readonly router = inject(Router);
  private readonly activatedRoute = inject(ActivatedRoute);
  readonly airportForm = this.formBuilder.nonNullable.group({

    icaoCode: [
      '',
      [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(4)
      ]
    ],

    iataCode: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(3)
      ]
    ],

    name: [
      '',
      Validators.required
    ],

    city: [
      '',
      Validators.required
    ],

    country: [
      '',
      Validators.required
    ],

    latitude: [
      0,
      [
        Validators.required,
        Validators.min(-90),
        Validators.max(90)
      ]
    ],

    longitude: [
      0,
      [
        Validators.required,
        Validators.min(-180),
        Validators.max(180)
      ]
    ],

    elevation: [
      0,
      Validators.required
    ],

    status: [
      'ACTIVE',
      Validators.required
    ]

  });

  isSubmitting = false;

  successMessage = '';

  errorMessage = '';

  airportId: string | null = null;

  ngOnInit(): void {
    //detect edit mode
    this.airportId = this.activatedRoute.snapshot.paramMap.get('id');

    if(this.airportId){
        this.loadAirport(this.airportId);
    }

  }

  loadAirport(id: string): void {
    
    this.airportService.getAirportById(id).subscribe(
    {
      next: (airport) => {
        this.airportForm.patchValue(airport);
      },

      error: (error) => {
        console.error(
        'Error loading airport: ',
        error
        )
      }
    }
    );
  }

onSubmit(): void {

  if (this.airportForm.invalid) {
    this.airportForm.markAllAsTouched();
    return;
  }

  const airportRequest: AirportRequest =
    this.airportForm.getRawValue();

  // EDIT MODE
  if (this.airportId) {

    this.airportService
      .updateAirport(
        this.airportId,
        airportRequest
      )
      .subscribe({

        next: () => {

          console.log(
            'Airport updated successfully'
          );

          this.router.navigate(['/airports']);
        },

        error: (error) => {

          console.error(
            'Error updating airport:',
            error
          );

        }

      });

    return;
  }

  // CREATE MODE
  this.airportService
    .createAirport(airportRequest)
    .subscribe({

      next: () => {

        console.log(
          'Airport created successfully'
        );

        this.router.navigate(['/airports']);
      },

      error: (error) => {

        console.error(
          'Error creating airport:',
          error
        );

      }

    });
}

}