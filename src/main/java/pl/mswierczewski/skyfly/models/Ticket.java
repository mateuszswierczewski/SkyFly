package pl.mswierczewski.skyfly.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seatNumber;

    private Double price;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Flight flight;

    public Ticket(){

    }

    public Ticket(Integer seatNumber, Double price, Passenger passenger, Flight flight) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.passenger = passenger;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger owner) {
        this.passenger = owner;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }
}
