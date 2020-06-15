package pl.mswierczewski.skyfly.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectedFlights {

    private String departureFlights;
    private String arrivalFlights; //optional

    public String getDepartureFlight() {
        return departureFlights;
    }

    public void setDepartureFlight(String departureFlight) {
        this.departureFlights = departureFlight;
    }

    public String getArrivalFlight() {
        return arrivalFlights;
    }

    public void setArrivalFlight(String arrivalFlight) {
        this.arrivalFlights = arrivalFlight;
    }

    public List<Long> getAllFlightsIds(){
        List<Long> flightsIds = getDepartureFlightsIds();
        flightsIds.addAll(getArrivalFlightsIds());
        return flightsIds;
    }

    public List<Long> getDepartureFlightsIds(){
        return getIds(departureFlights);
    }

    public List<Long> getArrivalFlightsIds(){
        return getIds(arrivalFlights);
    }

    private List<Long> getIds(String str){
        List<Long> ids = new ArrayList<>();

        if(str != null) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(str);
            while (m.find()) {
                ids.add(Long.parseLong(m.group()));
            }
            return ids;
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SelectedFlights{" +
                "departureFlights=" + departureFlights +
                ", arrivalFlights=" + arrivalFlights +
                '}';
    }
}
