package pl.mswierczewski.skyfly.dtos;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class FlightRequest {

    @NotNull
    private String departureCity;

    @NotNull
    private String arrivalCity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate arrivalDate;

    private boolean inBothDirections;

    private boolean connectingFlight;

    @Min(1)
    @Max(5)
    private int numberOfPassenger;

    public FlightRequest(){

    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean isInBothDirections() {
        return inBothDirections;
    }

    public void setInBothDirections(boolean inBothDirections) {
        this.inBothDirections = inBothDirections;
    }

    public boolean isConnectingFlight() {
        return connectingFlight;
    }

    public void setConnectingFlight(boolean connectingFlight) {
        this.connectingFlight = connectingFlight;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "departure='" + departureCity + '\'' +
                ", arrival='" + arrivalCity + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", inBothDirections=" + inBothDirections +
                ", numberOfPassenger=" + numberOfPassenger +
                '}';
    }
}
