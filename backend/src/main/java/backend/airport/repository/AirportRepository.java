package backend.airport.repository;

import backend.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport, UUID> {
    Optional<Airport> findByIcaoCode(String icaoCode);
}
