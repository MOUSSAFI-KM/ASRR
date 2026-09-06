package backend.risk.hazard.controller;

import backend.risk.hazard.dto.HazardRequest;
import backend.risk.hazard.dto.HazardResponse;
import backend.risk.hazard.service.HazardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hazards")
public class HazardController {
    private final HazardService hazardService;

    public HazardController(HazardService hazardService) {
        this.hazardService = hazardService;
    }

    @GetMapping
    public List<HazardResponse> getAll() {
        return hazardService.getAll();
    }

    @GetMapping("/active")
    public List<HazardResponse> getActive() {
        return hazardService.getActive();
    }

    @GetMapping("/{id}")
    public HazardResponse getById(@PathVariable UUID id) {
        return hazardService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HazardResponse create(
            @Valid @RequestBody HazardRequest request
    ) {
        return hazardService.create(request);
    }

    @PutMapping("/{id}")
    public HazardResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody HazardRequest request
    ) {
        return hazardService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        hazardService.delete(id);
    }
}
