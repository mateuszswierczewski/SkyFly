package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.repositories.PassengerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPassengerService implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public DefaultPassengerService(PassengerRepository passengerRepository){
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Passenger> savePassengers(Collection<Passenger> passengers) {
        return passengers.stream()
                .map(passenger -> {
                    Passenger p = passengerRepository.getPassengerBy(
                                passenger.getFirstName(),
                                passenger.getSecondName(),
                                passenger.getLastName(),
                                passenger.getBirthDate())
                            .orElse(null);
                    if (p == null)
                        p = passengerRepository.saveAndFlush(passenger);
                    return p;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
