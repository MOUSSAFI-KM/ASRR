package backend.airport.mapper;

import backend.airport.dto.AirportRequest;
import backend.airport.dto.AirportResponse;
import backend.airport.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AirportMapper {

    Airport toEntity(AirportRequest request);

    AirportRequest toDto(Airport airport);
    AirportResponse toResponse(Airport airport);
    List<AirportResponse> toResponses(List<Airport> airport);
    void updateEntity(
            AirportRequest request,
            @MappingTarget Airport airport
    );
}
