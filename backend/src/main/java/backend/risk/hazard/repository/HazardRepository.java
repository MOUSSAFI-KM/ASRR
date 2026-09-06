package backend.risk.hazard.repository;

import backend.risk.hazard.entity.Hazard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HazardRepository extends JpaRepository<Hazard, UUID> {
    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    List<Hazard> findByActiveTrueOrderByNameAsc();
}
