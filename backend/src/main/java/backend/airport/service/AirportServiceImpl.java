package backend.airport.service;


import backend.airport.dto.AirportRequest;
import backend.airport.dto.AirportResponse;
import backend.airport.entity.Airport;
import backend.airport.mapper.AirportMapper;
import backend.airport.repository.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService{

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public AirportResponse createAirport(AirportRequest request) {
        Airport airport = airportMapper.toEntity(request);

        Airport savedAirport = airportRepository.save(airport);

        return airportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse  getAirportById(UUID id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Airport not found with id: " + id));

        return airportMapper.toResponse(airport);
    }

    @Override
    public AirportResponse  getAirportByIcaoCode(String icaoCode) {
        Airport airport = airportRepository.findByIcaoCode(icaoCode)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Airport not found with ICAO code: " + icaoCode));

        return airportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse > getAllAirports() {
        return airportMapper.toResponses( airportRepository.findAll());
    }

    @Override
    public void deleteAirport(UUID id) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException(
                    "Airport not found with id: " + id);
        }

        airportRepository.deleteById(id);
    }
}
