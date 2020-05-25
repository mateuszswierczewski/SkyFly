package pl.mswierczewski.skyfly.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.ContactDetails;
import pl.mswierczewski.skyfly.models.Passenger;


import static pl.mswierczewski.skyfly.security.user.SkyFlyUserRole.ROLE_PASSENGER;

@Service
public class DefaultSkyFlyUserService implements SkyFlyUserService {

    private final SkyFlyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultSkyFlyUserService(SkyFlyUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public void registerUserAsPassenger(SkyFlyUser user, Passenger passenger, ContactDetails contactDetails){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRole(ROLE_PASSENGER);
        passenger.setContactDetails(contactDetails);
        user.setUserDetails(passenger);
        userRepository.save(user);
    }

    @Override
    public boolean isValueExists(Object value, String columnName) throws UnsupportedOperationException {
        if (columnName.equals("username")) return userRepository.existsByUsername(value.toString());

        throw new UnsupportedOperationException("Column \"" + columnName + "\" not supported!");
    }
}
