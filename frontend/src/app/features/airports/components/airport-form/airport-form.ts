import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AirportService } from '../../services/airport.service';

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


  onSubmit(): void {

    this.successMessage = '';
    this.errorMessage = '';

    if (this.airportForm.invalid) {

      this.airportForm.markAllAsTouched();

      return;
    }

    this.isSubmitting = true;

    const request = this.airportForm.getRawValue();

    
    this.airportService.createAirport(request).subscribe({

      next: (airport) => {

        console.log('Airport created:', airport);

        this.router.navigate(['/airports']);

        this.isSubmitting = false;

      },

      error: (error) => {

        console.error(
          'Error while creating airport:',
          error
        );

        this.errorMessage =
          'Unable to create the airport. Please try again.';

        this.isSubmitting = false;

      }

    });

  }

}