package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.Airport;
import pl.mswierczewski.skyfly.repositories.ContactDetailsRepository;

@Service
public class DefaultContactDetailsService implements ContactDetailsService {

    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    public DefaultContactDetailsService(ContactDetailsRepository contactDetailsRepository){
        this.contactDetailsRepository = contactDetailsRepository;
    }

    @Override
    public boolean isValueExists(Object value, String columnName) throws UnsupportedOperationException {
        if (columnName.equals("email")) return contactDetailsRepository.existsByEmail(value.toString());

        throw new UnsupportedOperationException("Column \"" + columnName + "\" not supported!");
    }
}
