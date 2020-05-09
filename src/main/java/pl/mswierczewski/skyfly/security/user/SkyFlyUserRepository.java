package pl.mswierczewski.skyfly.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkyFlyUserRepository extends JpaRepository<SkyFlyUser, String> {

    Optional<SkyFlyUser> findByUsername(String username);

}
