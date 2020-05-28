package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.dtos.CountryCityDTO;
import pl.mswierczewski.skyfly.repositories.AirportRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultAirportService implements AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public DefaultAirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    @Override
    public boolean isValueExists(Object value, String columnName) throws UnsupportedOperationException {
        if (columnName.equals("airportCode")) return airportRepository.existsByAirportCode(value.toString());
        return false;
    }

    @Override
    public Map<String, List<String>> getAllCitiesGroupByCountry() {
        return airportRepository.getAllCitiesWithCountry().stream()
                .collect(Collectors.groupingBy(
                        CountryCityDTO::getCountry,
                        Collectors.mapping(CountryCityDTO::getCity, Collectors.toList())
                ));
    }

    @Override
    public Set<String> getAirportsCodesByCity(String departureCity) {
        return airportRepository.findAirportsCodes(departureCity);
    }
}
