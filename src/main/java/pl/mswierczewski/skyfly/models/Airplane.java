package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.AirplaneType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airplanes")
public class Airplane implements Serializable {

    @Id
    @Column(name = "id_airplane")
    @NotNull
    private String id;

    @NotNull
    private String producent;

    @NotNull
    private String model;

    @NotNull
    @Min(2)
    private Integer numbersOfPilots; // Numbers of pilots required to operate the airplane

    @NotNull
    @Enumerated(EnumType.STRING)
    private AirplaneType type;

    @NotNull
    private Integer seats;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flights = new ArrayList<>();

    public Airplane() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumbersOfPilots() {
        return numbersOfPilots;
    }

    public void setNumbersOfPilots(Integer numbersOfPilots) {
        this.numbersOfPilots = numbersOfPilots;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
