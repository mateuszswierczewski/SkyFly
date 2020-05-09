package pl.mswierczewski.skyfly.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static pl.mswierczewski.skyfly.security.user.SkyFlyUserRole.ROLE_PASSENGER;

@Service
public class SkyFlyUserService implements UserDetailsService {

    private SkyFlyUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SkyFlyUserService(SkyFlyUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("here");
        System.out.println(userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username))));
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public void registerUserAsPassenger(SkyFlyUser user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRole(ROLE_PASSENGER);
        userRepository.save(user);
    }
}
