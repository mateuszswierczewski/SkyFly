package pl.mswierczewski.skyfly.dtos;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SearchFlightRequest {

    @NotNull
    private String departureCity;

    @NotNull
    private String arrivalCity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate arrivalDate;

    private boolean inBothDirections = true;

    private boolean connectingFlight;

    @Min(1)
    @Max(5)
    private Integer numberOfPassenger;

    public SearchFlightRequest(){

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

    public Integer getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(Integer numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    public SearchFlightRequest reverseDirection(){
        String tmp = this.departureCity;
        this.departureCity = this.arrivalCity;
        this.arrivalCity = tmp;
        return this;
    }

    @Override
    public String toString() {
        return "SearchFlightRequest{" +
                "departure='" + departureCity + '\'' +
                ", arrival='" + arrivalCity + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", inBothDirections=" + inBothDirections +
                ", numberOfPassenger=" + numberOfPassenger +
                '}';
    }
}
