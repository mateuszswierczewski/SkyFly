package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.dtos.SearchFlightRequest;
import pl.mswierczewski.skyfly.dtos.SearchFlightResponse;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.repositories.FlightRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultFlightService implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final TicketService ticketService;

    @Autowired
    public DefaultFlightService(FlightRepository flightRepository, AirportService airportService, TicketService ticketService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
        this.ticketService = ticketService;
    }

    @Override
    public List<SearchFlightResponse> getMatchingFlights(SearchFlightRequest searchFlightRequest) {
        Set<String> departureAirportsCodes = airportService.getAirportsCodesByCity(searchFlightRequest.getDepartureCity());
        Set<String> arrivalAirportsCodes = airportService.getAirportsCodesByCity(searchFlightRequest.getArrivalCity());

        List<Flight> directFlights = new ArrayList<>();

        for (String departureAirportCode : departureAirportsCodes) {
            for (String arrivalAirportCode : arrivalAirportsCodes) {
                directFlights.addAll(flightRepository.findDirectFlightsForPassenger(
                        departureAirportCode,
                        arrivalAirportCode,
                        searchFlightRequest.getDepartureDate().atTime(LocalTime.now()),
                        PageRequest.of(0, 10)
                ));
            }
        }

        return directFlights.stream()
                .map(flight -> new SearchFlightResponse(
                        flight.getId(),
                        searchFlightRequest.getDepartureCity(),
                        searchFlightRequest.getArrivalCity(),
                        flight.getDepartureDateTime(),
                        flight.getEstimatedArrivalDateTime(),
                        flight.getDistance(),
                        flight.getStandardPrice(),
                        flight.getAirplane().getSeats() - ticketService.getNumberOfTicketSoldFor(flight)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public List<Flight> getFlightsByIds(List<Long> ids) {
        return ids.stream()
                .map(this::getFlightById)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Integer getNumberOfOccupiedSeats(Flight flight) {
        return null;
    }
}
