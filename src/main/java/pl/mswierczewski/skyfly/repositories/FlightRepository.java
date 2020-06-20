package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.Flight;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query ("SELECT f " +
            "FROM Flight f " +
            "WHERE f.departureAirport.airportCode = :departureAirportCode " +
            "AND f.arrivalAirport.airportCode = :arrivalAirportCode " +
            "AND f.departureDateTime >= :departureDate " +
            "ORDER BY f.departureDateTime")
    List<Flight> findDirectFlights(@Param(value = "departureAirportCode") String departureAirportCode,
                                   @Param(value = "arrivalAirportCode") String arrivalAirportCode,
                                   @Param(value = "departureDate") LocalDateTime departureDate,
                                   Pageable pageable);

    @Query ("SELECT f1, f2 " +
            "FROM Flight f1 " +
            "JOIN Flight f2 ON f1.arrivalAirport = f2.departureAirport " +
            "AND (f1.estimatedArrivalDateTime < f2.estimatedArrivalDateTime) " +
            "WHERE f1.departureAirport.airportCode = :departureAirportCode " +
            "AND f2.arrivalAirport.airportCode = :arrivalAirportCode " +
            "AND f1.departureDateTime >= :departureDate")
    List<List<Flight>> findConnectedFlightsWithOneStop(@Param(value = "departureAirportCode") String departureAirportCode,
                                                 @Param(value = "arrivalAirportCode") String arrivalAirportCode,
                                                 @Param(value = "departureDate") LocalDateTime departureDate,
                                                 Pageable pageable);



    Flight getById(Long id);

}
