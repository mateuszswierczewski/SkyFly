package pl.mswierczewski.skyfly.dtos;

public class CountryCityDTO {

    private String country;
    private String city;

    public CountryCityDTO(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
