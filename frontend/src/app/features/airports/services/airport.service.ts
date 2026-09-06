import { Injectable, inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Airport } from "../models/airport.model";
import { AirportRequest } from "../models/airport-request.model";

@Injectable({
    providedIn:"root"
})

export class AirportService{

    private readonly http = inject(HttpClient);

    private readonly apiUrl = 'http://localhost:8080/api/airports';

    getAllAirports(): Observable<Airport[]> {
    return this.http.get<Airport[]>(this.apiUrl);//this is is exactly --> GET http://localhost:8080/api/airports
    }

    createAirport(request: AirportRequest): Observable<Airport>{
        return this.http.post<Airport>(this.apiUrl, request);//this is is exactly --> POST http://localhost:8080/api/airports + request
    }

    getAirportById(id: string ): Observable<Airport> {
        return this.http.get<Airport>(`${this.apiUrl}/${id}`);
    }

    updateAirport(id: string, request: AirportRequest): Observable<Airport> {
        return this.http.put<Airport>(`${this.apiUrl}/${id}`, request);
    }
    searchAirports(search: string): Observable<Airport[]> {
  return this.http.get<Airport[]>(
    `${this.apiUrl}?search=${encodeURIComponent(search)}`
  );
}
deleteAirport(id: string): Observable<void> {
  return this.http.delete<void>(
    `${this.apiUrl}/${id}`
  );
}

}