package pl.mswierczewski.skyfly.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "passengers")
@DiscriminatorValue("Passenger")
public class Passenger extends Person implements Serializable {

    @OneToMany(mappedBy = "passenger")
    List<Ticket> tickets = new ArrayList<>();

    public Passenger(){ super(); }

    public Passenger(String firstName, String secondName, String lastName, LocalDate birthDate){
        super(firstName, secondName, lastName, birthDate);
    }

    public Passenger(String firstName, String lastName, LocalDate birthDate){
        super(firstName, lastName, birthDate);
    }

    public double getDiscount(){
        int age = getAge();

        if (age < 7){
            return 0.2D;
        } else if (age < 12){
            return 0.5D;
        } else if (age < 16){
            return 0.8D;
        } else {
            return 1;
        }
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
