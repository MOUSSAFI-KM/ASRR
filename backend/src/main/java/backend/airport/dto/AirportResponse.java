package backend.airport.dto;

import backend.airport.entity.AirportStatus;

import java.util.UUID;

public record AirportResponse (
    UUID id,
    String icaoCode,
    String iataCode,
    String name,
    String city,
    String country,
    Double latitude,
    Double longitude,
    Integer elevation,
    AirportStatus status
)
        {
}
