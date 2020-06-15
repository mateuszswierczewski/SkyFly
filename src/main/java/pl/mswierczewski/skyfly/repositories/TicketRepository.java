package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Integer countTicketsByFlight(Flight flight);
}
