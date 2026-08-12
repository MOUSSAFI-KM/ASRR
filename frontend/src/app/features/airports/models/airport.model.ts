export interface Airport {
  id: string;
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