package pl.mswierczewski.skyfly.services;

import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.models.Passenger;

import java.util.List;

public interface TicketService {

    Integer getNumberOfTicketSoldFor(Flight flight);

    void generateTicketsAndSave(List<Flight> flights, List<Passenger> passengers) throws Exception;
}
