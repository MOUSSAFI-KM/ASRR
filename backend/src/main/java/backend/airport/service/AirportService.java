package backend.airport.service;


import backend.airport.dto.AirportRequest;
import backend.airport.dto.AirportResponse;

import java.util.List;
import java.util.UUID;

public interface AirportService {
    AirportResponse  createAirport(AirportRequest request);

    AirportResponse getAirportById(UUID id);

    AirportResponse  getAirportByIcaoCode(String icaoCode);

    List<AirportResponse > getAllAirports();

    List<AirportResponse> searchAirports(String search);

    void deleteAirport(UUID id);
    public AirportResponse updateAirport(UUID id, AirportRequest request);
}
