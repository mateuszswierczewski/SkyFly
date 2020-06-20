package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.services.AirportService;
import pl.mswierczewski.skyfly.validation.constraints.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "airports")
public class Airport implements Serializable {

    @Id
    @NotNull
    @Unique(serviceClass = AirportService.class, columnName = "airportCode")
    private String airportCode;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    @Column(precision = 10, scale = 13)
    private double latitude;

    @NotNull
    @Column(precision = 10, scale = 13)
    private double longitude;

    public Airport() {
    }

    public Airport(String airportCode, String name, String city, String country, double latitude, double longitude) {
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
