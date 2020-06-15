package pl.mswierczewski.skyfly.services;

import pl.mswierczewski.skyfly.dtos.SearchFlightRequest;
import pl.mswierczewski.skyfly.dtos.SearchFlightResponse;
import pl.mswierczewski.skyfly.models.Flight;

import java.util.List;

public interface FlightService {

    List<SearchFlightResponse> getMatchingFlights(SearchFlightRequest searchFlightRequest);

    Flight getFlightById(Long id);

    List<Flight> getFlightsByIds(List<Long> ids);

    Integer getNumberOfOccupiedSeats(Flight flight);
}
