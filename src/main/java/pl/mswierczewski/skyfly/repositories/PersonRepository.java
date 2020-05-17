package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mswierczewski.skyfly.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
