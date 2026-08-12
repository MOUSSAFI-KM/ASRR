export interface AirportRequest {
  icaoCode: string;
  iataCode: string;
  name: string;
  city: string;
  country: string;
  latitude: number;
  longitude: number;
  elevation: number;
  status: string;
}