package pl.mswierczewski.skyfly.dtos;

import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.models.Passenger;

import java.time.LocalDateTime;
import java.util.List;

public class FlightSummaryResponse {

    private final long flightId;
    private final String from;
    private final String to;
    private final LocalDateTime departureTime;

    private int numberOfAdults = 0;
    private int numberOfTeenagers = 0;
    private int numberOfChildren = 0;
    private int numberOfSmallChildren = 0;

    private double sum;


    public FlightSummaryResponse(Flight flight, List<Passenger> passengers) {
        this.flightId = flight.getId();
        this.from = flight.getDepartureAirport().getCity();
        this.to = flight.getArrivalAirport().getCity();
        this.departureTime = flight.getDepartureDateTime();

        passengers.forEach(
                passenger -> {
                    specifyAgeGroup(passenger.getAge());
                    sum += flight.getStandardPrice() * passenger.getDiscount();
                }
        );

    }

    private void specifyAgeGroup(int age){
        if (age < 7){
            numberOfSmallChildren++;
        } else if (age < 12){
            numberOfChildren++;
        } else if (age < 16){
            numberOfTeenagers++;
        } else {
            numberOfAdults++;
        }
    }

    public long getFlightId() {
        return flightId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfTeenagers() {
        return numberOfTeenagers;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public int getNumberOfSmallChildren() {
        return numberOfSmallChildren;
    }

    public double getSum() {
        return sum;
    }
}
