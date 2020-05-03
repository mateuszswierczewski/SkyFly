package pl.mswierczewski.skyfly.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "passengers")
@DiscriminatorValue("Passenger")
public class Passenger extends Person implements Serializable {

    public Passenger(){ super(); }

    public Passenger(String firstName, String secondName, String lastName, String identityCardNumber, LocalDate birthDate){
        super(firstName, secondName, lastName, identityCardNumber, birthDate);
    }

    public Passenger(String firstName, String lastName, String identityCardNumber, LocalDate birthDate){
        super(firstName, lastName, identityCardNumber, birthDate);
    }



}
