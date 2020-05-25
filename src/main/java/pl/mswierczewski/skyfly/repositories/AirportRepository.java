package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    boolean existsByAirportCode(String airportCode);
}
