import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    loadComponent: () =>
      import('./core/layout/app-layout/app-layout')
        .then(m => m.AppLayout),

    children: [

      {
        path: 'airports',
        loadComponent: () =>
          import(
            './features/airports/components/airport-list/airport-list'
          ).then(m => m.AirportList)
      },

      {
        path: 'airports/new',
        loadComponent: () =>
          import(
            './features/airports/components/airport-form/airport-form'
          ).then(m => m.AirportForm)
      },

      {
        path: '',
        redirectTo: 'airports',
        pathMatch: 'full'
      }

    ]
  },

  {
    path: '**',
    redirectTo: 'airports'
  }

];