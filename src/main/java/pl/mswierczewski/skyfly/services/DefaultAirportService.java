package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.Airport;
import pl.mswierczewski.skyfly.repositories.AirportRepository;

@Service
public class DefaultAirportService implements AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public DefaultAirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    @Override
    public boolean isValueExists(Object value, String columnName) throws UnsupportedOperationException {
        if (columnName.equals("airportCode")) return airportRepository.existsByAirportCode(value.toString());
        return false;
    }
}
