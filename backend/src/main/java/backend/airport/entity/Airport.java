package backend.airport.entity;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

//validation des champs
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import backend.common.entity.AuditableEntity;
import java.util.UUID;

@Data
@Entity
@Table(name = "airport")
public class Airport extends AuditableEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @NotBlank
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[A-Z]{4}$")
    @Column(name = "icao_code", length = 4, nullable = false, unique = true)
    private String icaoCode;

    @NotBlank
    @Size(min = 3, max = 3)
    @Pattern(regexp = "^[A-Z]{3}$")
    @Column(name = "iata_code", length = 3, nullable = false, unique = true )
    private String iataCode;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @NotBlank
    @Size(max = 100)
    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @NotNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    @Column(nullable = false)
    private Double latitude;

    @NotNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    @Column(nullable = false)
    private Double longitude;

    @NotNull
    @Column(nullable = false)
    private Integer elevation;

    @Enumerated(EnumType.STRING)
    private AirportStatus status;
}
