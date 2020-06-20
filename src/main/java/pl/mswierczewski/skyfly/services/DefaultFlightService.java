package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.dtos.SearchFlightRequest;
import pl.mswierczewski.skyfly.dtos.SearchFlightResponse;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.repositories.FlightRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

        List<Flight> flights = new ArrayList<>();

        for (String departureAirportCode : departureAirportsCodes) {
            for (String arrivalAirportCode : arrivalAirportsCodes) {
                flights.addAll(flightRepository.findDirectFlights(
                        departureAirportCode,
                        arrivalAirportCode,
                        searchFlightRequest.getDepartureDate().atTime(LocalTime.now()),
                        PageRequest.of(0, 10)
                ));
            }
        }

        List<SearchFlightResponse> response = flights.stream()
                .map(flight -> new SearchFlightResponse(
                        flight.getId(),
                        searchFlightRequest.getDepartureCity(),
                        searchFlightRequest.getArrivalCity(),
                        flight.getDepartureDateTime(),
                        flight.getEstimatedArrivalDateTime(),
                        flight.getDistance(),
                        flight.getStandardPrice(),
                        flight.getAirplane().getSeats() - ticketService.getNumberOfTicketSoldFor(flight)))
                .collect(Collectors.toList());

        if (searchFlightRequest.isConnectingFlight()){
            List<List<Flight>> flightsWithStops = new ArrayList<>();

            for (String departureAirportCode : departureAirportsCodes) {
                for (String arrivalAirportCode : arrivalAirportsCodes) {
                    flightsWithStops.addAll(flightRepository.findConnectedFlightsWithOneStop(
                            departureAirportCode,
                            arrivalAirportCode,
                            searchFlightRequest.getDepartureDate().atTime(LocalTime.now()),
                            PageRequest.of(0, 10)
                    ));
                }
            }

            response.addAll(
                    flightsWithStops.stream()
                        .filter(flightList -> {
                            //tmp solution
                            return ChronoUnit.HOURS.between(flightList.get(0).getEstimatedArrivalDateTime(), flightList.get(1).getDepartureDateTime()) < 36;
                        })
                        .map(flightsList -> {
                            List<Long> ids = flightsList.stream()
                                    .map(Flight::getId)
                                    .collect(Collectors.toList());

                            LocalDateTime departureDateTime = flightsList.stream()
                                    .map(Flight::getDepartureDateTime)
                                    .min(LocalDateTime::compareTo)
                                    .orElse(null);

                            LocalDateTime arrivalDateTime = flightsList.stream()
                                    .map(Flight::getEstimatedArrivalDateTime)
                                    .max(LocalDateTime::compareTo)
                                    .orElse(null);

                            Double fullPrice = flightsList.stream()
                                    .map(Flight::getStandardPrice)
                                    .reduce(0D, Double::sum);

                            List<String> stops = flightsList.stream()
                                    .map(flight -> flight.getArrivalAirport().getCity())
                                    .collect(Collectors.toList());
                            stops.remove(stops.size() - 1);

                            Set<Integer> numberOfFreeSeats = flightsList.stream()
                                    .map(flight -> flight.getAirplane().getSeats() - ticketService.getNumberOfTicketSoldFor(flight))
                                    .collect(Collectors.toSet());

                            return new SearchFlightResponse(
                                    ids,
                                    searchFlightRequest.getDepartureCity(),
                                    searchFlightRequest.getArrivalCity(),
                                    stops,
                                    departureDateTime,
                                    arrivalDateTime,
                                    fullPrice,
                                    numberOfFreeSeats
                            );

                        }).collect(Collectors.toList())
            );
        }

        response.sort((flight1, flight2) -> {
            if (flight1.getDepartureDateTime().isBefore(flight2.getDepartureDateTime())){
                return -1;
            } else if (flight1.getDepartureDateTime().isAfter(flight2.getDepartureDateTime())){
                return 1;
            } else {
                return 0;
            }
        });

        return response;
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
