package backend.airport.mapper;

import backend.airport.dto.AirportRequest;
import backend.airport.dto.AirportResponse;
import backend.airport.entity.Airport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    Airport toEntity(AirportRequest request);

    AirportRequest toDto(Airport airport);
    AirportResponse toResponse(Airport airport);
    List<AirportResponse> toResponses(List<Airport> airport);
}
