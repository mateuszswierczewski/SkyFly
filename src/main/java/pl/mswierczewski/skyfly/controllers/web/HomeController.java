package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.mswierczewski.skyfly.dtos.FlightRequest;

import pl.mswierczewski.skyfly.services.AirportService;


@Controller
@SessionAttributes("allCitiesWithCountry")
public class HomeController {

    private AirportService airportService;

    @Autowired
    public HomeController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public String home(Model model){
        if (!model.containsAttribute("allCitiesWithCountry")) {
            model.addAttribute("allCitiesWithCountry", airportService.getAllCitiesGroupByCountry());
        }
        model.addAttribute("flightRequest", new FlightRequest());
        return "index";
    }
}
