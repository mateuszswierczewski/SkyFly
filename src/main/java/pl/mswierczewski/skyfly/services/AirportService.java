package pl.mswierczewski.skyfly.services;

import pl.mswierczewski.skyfly.services.common.ValueExists;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AirportService extends ValueExists {

    Map<String, List<String>> getAllCitiesGroupByCountry();

    Set<String> getAirportsCodesByCity(String departureCity);
}

