package pl.mswierczewski.skyfly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mswierczewski.skyfly.models.User;
import pl.mswierczewski.skyfly.models.UserRole;
import pl.mswierczewski.skyfly.models.enums.UserType;
import pl.mswierczewski.skyfly.repositories.UserRepository;
import pl.mswierczewski.skyfly.repositories.UserRoleRepository;

@Service
public class UserService {

    private static final UserType DEFAULT_ROLE = UserType.ROLE_PASSENGER;

    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findUserRoleByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

}
