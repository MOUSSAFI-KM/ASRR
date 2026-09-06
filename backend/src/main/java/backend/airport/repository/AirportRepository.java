package backend.airport.repository;

import backend.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport, UUID> {
    Optional<Airport> findByIcaoCode(String icaoCode);

    @Query("""
    SELECT a
    FROM Airport a
    WHERE LOWER(a.icaoCode) LIKE LOWER(CONCAT('%', :search, '%'))
       OR LOWER(a.iataCode) LIKE LOWER(CONCAT('%', :search, '%'))
       OR LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%'))
       OR LOWER(a.city) LIKE LOWER(CONCAT('%', :search, '%'))
       OR LOWER(a.country) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    List<Airport> search(@Param("search") String search);
}
