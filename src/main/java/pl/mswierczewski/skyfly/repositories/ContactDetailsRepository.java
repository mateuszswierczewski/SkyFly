package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mswierczewski.skyfly.models.ContactDetails;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

    boolean existsByEmail(String email);
}
