package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mswierczewski.skyfly.models.Passenger;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Long> {


    @Query ("SELECT p FROM passengers p " +
            "WHERE p.firstName = :firstName " +
            "AND p.secondName = :secondName " +
            "AND p.lastName = :lastName " +
            "AND p.birthDate = :birthDate")
    Optional<Passenger> getPassengerBy(@Param(value = "firstName") String firstName,
                                       @Param(value = "secondName") String secondName,
                                       @Param(value = "lastName") String lastName,
                                       @Param(value = "birthDate")LocalDate birthDate);
}
