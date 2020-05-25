package pl.mswierczewski.skyfly.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime startFlightDateTime;

    @NotNull
    private Double standardPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Airplane airplane;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Airport departureAirport;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Airport arrivalAirport;

    public Flight(){

    }
}
