package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String> {

}
