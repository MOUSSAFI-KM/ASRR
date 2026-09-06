package backend.risk.hazard.service;

import backend.risk.hazard.dto.HazardRequest;
import backend.risk.hazard.dto.HazardResponse;
import backend.risk.hazard.entity.Hazard;
import backend.risk.hazard.mapper.HazardMapper;
import backend.risk.hazard.repository.HazardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class HazardService {
    private final HazardRepository hazardRepository;
    private final HazardMapper hazardMapper;

    public HazardService(HazardRepository hazardRepository, HazardMapper hazardMapper) {
        this.hazardRepository = hazardRepository;
        this.hazardMapper = hazardMapper;
    }

    public List<HazardResponse> getActive(){
        return hazardMapper.toResponseList(hazardRepository
                .findByActiveTrueOrderByNameAsc());

    }

    public HazardResponse getById(UUID id) {

        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Hazard not found"
                        )
                );

        return hazardMapper.toResponse(hazard);
    }

    public HazardResponse create(HazardRequest request) {

        if (hazardRepository.existsByCode(request.getCode())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Hazard code already exists"
            );
        }

        Hazard hazard = hazardMapper.toEntity(request);

        Hazard savedHazard = hazardRepository.save(hazard);

        return hazardMapper.toResponse(savedHazard);
    }

    public HazardResponse update(UUID id, HazardRequest request) {

        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Hazard not found"
                        )
                );

        if (hazardRepository.existsByCodeAndIdNot(
                request.getCode(),
                id
        )) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Hazard code already exists"
            );
        }

        hazardMapper.updateEntity(request, hazard);

        Hazard updatedHazard = hazardRepository.save(hazard);

        return hazardMapper.toResponse(updatedHazard);
    }

    public void delete(UUID id) {

        if (!hazardRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Hazard not found"
            );
        }

        hazardRepository.deleteById(id);
    }

    public List<HazardResponse> getAll(){
        return hazardMapper.toResponseList(hazardRepository.findAll());
    }

}
