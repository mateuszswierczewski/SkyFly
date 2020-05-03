package pl.mswierczewski.skyfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.mswierczewski.skyfly.models.ContactDetails;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.repositories.PassengerRepository;

import java.time.LocalDate;

@SpringBootApplication
public class SkyFlyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SkyFlyApplication.class, args);

        /*Passenger mati = new Passenger("Mateusz", "Swierczewski", "XYZ", LocalDate.of(1998, 1, 16));
        ContactDetails matiContact = new ContactDetails("Chrzczanka-Folwark 24a", 881622488, "mate9816@gmail.com");
        mati.setContactDetails(matiContact);

        PassengerRepository repository = ctx.getBean(PassengerRepository.class);

        repository.save(mati);

*/

    }

}
