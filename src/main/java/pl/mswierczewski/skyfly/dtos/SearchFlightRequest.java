package pl.mswierczewski.skyfly.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
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
    @Future(message = "Departure date must be set in future")
    private LocalDate departureDate = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Departure date must be set in future")
    private LocalDate arrivalDate;

    private boolean inOneWay = true;

    private boolean connectingFlight = false;

    @Min(1)
    @Max(5)
    private Integer numberOfPassenger = 1;

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

    public boolean isInOneWay() {
        return inOneWay;
    }

    public void setInOneWay(boolean inOneWay) {
        this.inOneWay = inOneWay;
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

    public SearchFlightRequest reverseDirectionAndDate(){
        String tmp = this.departureCity;
        this.departureCity = this.arrivalCity;
        this.arrivalCity = tmp;
        LocalDate date = this.departureDate;
        this.departureDate = this.arrivalDate;
        this.arrivalDate = date;
        return this;
    }

    @Override
    public String toString() {
        return "SearchFlightRequest{" +
                "departure='" + departureCity + '\'' +
                ", arrival='" + arrivalCity + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", inBothDirections=" + inOneWay +
                ", numberOfPassenger=" + numberOfPassenger +
                '}';
    }
}
