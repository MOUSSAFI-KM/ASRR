package backend.risk.hazard.mapper;

import backend.risk.hazard.dto.HazardRequest;
import backend.risk.hazard.dto.HazardResponse;
import backend.risk.hazard.entity.Hazard;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring" , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HazardMapper {
    Hazard toEntity(HazardRequest request);

    HazardResponse toResponse(Hazard hazard);

    List<HazardResponse> toResponseList(List<Hazard> hazards);

    void updateEntity(
            HazardRequest request,
            @MappingTarget Hazard hazard
    );
}
