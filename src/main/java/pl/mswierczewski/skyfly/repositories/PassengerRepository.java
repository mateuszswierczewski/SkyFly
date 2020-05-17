package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mswierczewski.skyfly.models.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
