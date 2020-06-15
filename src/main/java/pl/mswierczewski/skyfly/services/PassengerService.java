package pl.mswierczewski.skyfly.services;

import pl.mswierczewski.skyfly.models.Passenger;

import java.util.Collection;
import java.util.List;

public interface PassengerService {

    List<Passenger> savePassengers(Collection<Passenger> passengers);
}
