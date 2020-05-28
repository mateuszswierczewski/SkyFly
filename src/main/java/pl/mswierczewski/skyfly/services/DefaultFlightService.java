package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.dtos.FlightRequest;
import pl.mswierczewski.skyfly.dtos.FlightResponse;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.repositories.FlightRepository;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultFlightService implements FlightService {

    private FlightRepository flightRepository;
    private AirportService airportService;

    @Autowired
    public DefaultFlightService(FlightRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    @Override
    public List<FlightResponse> getFlights(FlightRequest flightRequest) {
        Set<String> depertureAirportsCodes = airportService.getAirportsCodesByCity(flightRequest.getDepartureCity());
        Set<String> arrivalAirportsCodes = airportService.getAirportsCodesByCity(flightRequest.getArrivalCity());

        List<Flight> flights = new ArrayList<>();

        for (String departureAirportCode : depertureAirportsCodes){
            for (String arrivalAirportCode : arrivalAirportsCodes){
                flights.addAll(flightRepository.findDirectFlightsForPassenger(
                        departureAirportCode,
                        arrivalAirportCode,
                        flightRequest.getDepartureDate().atTime(LocalTime.now()),
                        PageRequest.of(0, 10)
                ));
            }
        }
/*
        if (flightRequest.isConnectingFlight()){
            depertureAirportsCodes.forEach(
                    departureAirportCode -> {
                        arrivalAirportsCodes.forEach(
                                arrivalAirportCode ->{
                                    flights.addAll(flightRepository.findConnectedFlightsWithOneStopForPassenger());
                                }
                        );
                    }
            );
        }

 */
/*
        var flights = flightRepository.findDirectFlightsForPassenger(
                flightRequest.getDepartureCity(),
                flightRequest.getArrivalCity(),
                flightRequest.getDepartureDate().atTime(LocalTime.now()),
                PageRequest.of(0, 10)
        );
*/

        return flights.stream()
                .map(flight -> new FlightResponse(
                        flight.getId(),
                        flightRequest.getDepartureCity(),
                        flightRequest.getArrivalCity(),
                        flight.getDepartureDateTime(),
                        flight.getEstimatedArrivalDateTime(),
                        flight.getDistance(),
                        flight.getStandardPrice()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.getOne(id);
    }
}
