package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);
}
