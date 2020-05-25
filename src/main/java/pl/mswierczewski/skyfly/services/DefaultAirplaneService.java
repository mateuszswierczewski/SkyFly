package pl.mswierczewski.skyfly.services;

import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.repositories.AirplaneRepository;

@Service
public class DefaultAirplaneService implements AirplaneService {

    private AirplaneRepository airplaneRepository;

    public DefaultAirplaneService(AirplaneRepository airplaneRepository){
        this.airplaneRepository = airplaneRepository;
    }
}
