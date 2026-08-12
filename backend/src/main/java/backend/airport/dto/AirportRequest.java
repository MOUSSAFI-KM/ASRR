package backend.airport.dto;

import backend.airport.entity.AirportStatus;
import lombok.Data;

@Data
public class AirportRequest {
    private String icaoCode;

    private String iataCode;

    private String name;

    private String city;

    private String country;

    private Double latitude;

    private Double longitude;

    private Integer elevation;

    private AirportStatus status;
}
