package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.AirplaneStatus;
import pl.mswierczewski.skyfly.models.enums.AirplaneType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "Airplanes")
public class Airplane implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long id;

    @Column(nullable = false)
    private String producent;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    @Min(2)
    private Integer numbersOfPilots; // Numbers of pilots required to operate the airplane

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AirplaneType type;

    @Column(nullable = false)
    private Integer seats;


}
