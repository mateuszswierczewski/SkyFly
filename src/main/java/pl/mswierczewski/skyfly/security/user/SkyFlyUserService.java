package pl.mswierczewski.skyfly.security.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.mswierczewski.skyfly.models.ContactDetails;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.services.common.ValueExists;

public interface SkyFlyUserService extends UserDetailsService, ValueExists {

    void registerUserAsPassenger(SkyFlyUser user, Passenger passenger, ContactDetails contactDetails);
}
