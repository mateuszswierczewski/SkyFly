package pl.mswierczewski.skyfly.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFlightResponse {

    private List<Long> ids = new ArrayList<>();
    private String departureCity;
    private String arrivalCity;
    private List<String> stops = new ArrayList<>();
    private LocalDateTime departureDateTime;
    private LocalDateTime estimatedArrivalDateTime;
    private Integer distance;
    private Double price;
    private Set<Integer> numberOfFreePlaces = new HashSet<>();

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

    public SearchFlightResponse(List<Long> ids, String departureCity, String arrivalCity, List<String> stops,
                                LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Double fullPrice, Set<Integer> numberOfFreePlaces) {
        this.ids = ids;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.stops = stops;
        this.departureDateTime = departureDateTime;
        this.estimatedArrivalDateTime = arrivalDateTime;
        this.price = fullPrice;
        this.numberOfFreePlaces = numberOfFreePlaces;
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
