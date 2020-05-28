package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.dtos.CountryCityDTO;
import pl.mswierczewski.skyfly.models.Airport;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    boolean existsByAirportCode(String airportCode);

    @Query ("SELECT new pl.mswierczewski.skyfly.dtos.CountryCityDTO(country, city) FROM Airport")
    List<CountryCityDTO> getAllCitiesWithCountry();

    //The method return Set because there can be more than one airport in a city
    @Query ("SELECT airportCode FROM Airport WHERE city = :city")
    Set<String> findAirportsCodes(@Param(value = "city") String city);
}
