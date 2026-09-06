package backend.airport.controller;

import backend.airport.dto.AirportRequest;
import backend.airport.dto.AirportResponse;
import backend.airport.service.AirportService;
import jakarta.validation.Valid;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportResponse > createAirport(
            @Valid @RequestBody AirportRequest request) {

        AirportResponse airport = airportService.createAirport(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(airport);
    }

    @GetMapping
    public List<AirportResponse> getAirports(
            @RequestParam(required = false) String search
    ) {
        return airportService.searchAirports(search);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse > getAirportById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                airportService.getAirportById(id)
        );
    }

    @GetMapping("/icao/{icaoCode}")
    public ResponseEntity<AirportResponse > getAirportByIcaoCode(
            @PathVariable String icaoCode) {

        return ResponseEntity.ok(
                airportService.getAirportByIcaoCode(icaoCode)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(
            @PathVariable UUID id) {

        airportService.deleteAirport(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public AirportResponse updateAirport(
            @PathVariable UUID id,
            @Valid @RequestBody AirportRequest request
    ) {
        return airportService.updateAirport(id, request);
    }
}
