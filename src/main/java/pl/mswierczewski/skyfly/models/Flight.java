package pl.mswierczewski.skyfly.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime departureDateTime;

    //This attribute is calculated before persist in database
    private LocalDateTime estimatedArrivalDateTime;

    @NotNull
    private Double standardPrice;

    //This attribute is calculated before persist in database
    private Integer distance;

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

    @PrePersist
    private void prePersist(){
        this.calculateDistance();
        this.calculateEstimatedArrivalDateTime();
    }

    private void calculateDistance(){
        double dLat = departureAirport.getLatitude();
        double dLon = departureAirport.getLongitude();

        double aLat = arrivalAirport.getLatitude();
        double aLon = arrivalAirport.getLongitude();

        double theta = dLon - aLon;
        double distance = Math.sin(Math.toRadians(aLat)) * Math.sin(Math.toRadians(dLat)) +
                          Math.cos(Math.toRadians(aLat)) * Math.cos(Math.toRadians(dLat)) *
                          Math.cos(Math.toRadians(theta));
        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);
        distance *= 60 * 1.1515 * 1.609344;

        this.distance = (int) Math.round(distance * 100) / 100;
    }

    private void calculateEstimatedArrivalDateTime(){
        if (this.distance == null){
            calculateDistance();
        }
        double time = (double) this.distance / airplane.getType().getAvgSpeed();

        int hours = (int) time;
        int minutes = (int) ((time - hours) * 60) + 15;

        this.estimatedArrivalDateTime = this.departureDateTime.plus(Duration.ofHours(hours).plusMinutes(minutes));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime startFlightDateTime) {
        this.departureDateTime = startFlightDateTime;
    }

    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getEstimatedArrivalDateTime() {
        if (estimatedArrivalDateTime == null){
          calculateEstimatedArrivalDateTime();
        }

        return estimatedArrivalDateTime;
    }

    public Integer getDistance(){
        if (distance == null){
            calculateDistance();
        }

        return distance;
    }
}
