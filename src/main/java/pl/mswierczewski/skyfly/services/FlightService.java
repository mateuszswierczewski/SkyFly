package pl.mswierczewski.skyfly.services;

import pl.mswierczewski.skyfly.dtos.FlightRequest;
import pl.mswierczewski.skyfly.dtos.FlightResponse;
import pl.mswierczewski.skyfly.models.Flight;

import java.util.List;

public interface FlightService {

    List<FlightResponse> getFlights(FlightRequest flightRequest);

    Flight getFlightById(Long id);
}
