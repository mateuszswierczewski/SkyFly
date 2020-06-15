package pl.mswierczewski.skyfly.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFlightResponse {

    private final List<Long> ids = new ArrayList<>();
    private String departureCity;
    private String arrivalCity;
    private final List<String> stops = new ArrayList<>();
    private LocalDateTime departureDateTime;
    private LocalDateTime estimatedArrivalDateTime;
    private Integer distance;
    private Double price;
    private final Set<Integer> numberOfFreePlaces = new HashSet<>();

    public SearchFlightResponse(){

    }

    public SearchFlightResponse(Long id, String departureCity, String arrivalCity, LocalDateTime departureDateTime,
                                LocalDateTime estimatedArrivalDateTime, Integer distance, Double price, Integer numberOfFreePlaces) {
        this.ids.add(id);
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.estimatedArrivalDateTime = estimatedArrivalDateTime;
        this.distance = distance;
        this.price = price;
        this.numberOfFreePlaces.add(numberOfFreePlaces);
    }

    public List<Long> getIds() {
        return ids;
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

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getEstimatedArrivalDateTime() {
        return estimatedArrivalDateTime;
    }

    public void setEstimatedArrivalDateTime(LocalDateTime estimatedArrivalDateTime) {
        this.estimatedArrivalDateTime = estimatedArrivalDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getStops() {
        return stops;
    }

    public Integer getNumberOfFreePlaces(){
        return numberOfFreePlaces.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public String toString() {
        return "SearchFlightResponse{" +
                "ids=" + ids +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", estimatedArrivalDateTime=" + estimatedArrivalDateTime +
                ", distance=" + distance +
                ", price=" + price +
                '}';
    }
}
