package pl.mswierczewski.skyfly.services;

import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.models.Ticket;
import pl.mswierczewski.skyfly.repositories.TicketRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class DefaultTicketService implements TicketService {

    private TicketRepository ticketRepository;
    private final PassengerService passengerService;

    public DefaultTicketService(TicketRepository ticketRepository, PassengerService passengerService) {
        this.ticketRepository = ticketRepository;
        this.passengerService = passengerService;
    }


    @Override
    @Transactional
    public void generateTicketsAndSave(List<Flight> flights, List<Passenger> passengers) throws Exception {
        passengers = passengerService.savePassengers(passengers);
        List<Ticket> tickets = generateTicketsFor(flights, passengers);
        ticketRepository.saveAll(tickets);

    }

    private List<Ticket> generateTicketsFor(List<Flight> flights, List<Passenger> allPassengers) throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        for (Flight flight : flights) {
            int seatNumber = getNumberOfTicketSoldFor(flight) + 1;
            int maxSeatsNumber = flight.getAirplane().getSeats();
            if(maxSeatsNumber - seatNumber <= allPassengers.size())
                throw new Exception("No places left!");

            for (Passenger passenger : allPassengers) {
                tickets.add(
                        new Ticket(
                                seatNumber,
                                flight.getStandardPrice() * passenger.getDiscount(),
                                passenger,
                                flight
                        )
                );
                seatNumber++;
            }
        }
        return tickets;
    }

    @Override
    public Integer getNumberOfTicketSoldFor(Flight flight) {
        return ticketRepository.countTicketsByFlight(flight);
    }

}
