package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.mswierczewski.skyfly.dtos.SearchFlightRequest;

import pl.mswierczewski.skyfly.services.AirportService;


@Controller
public class HomeController {

    private AirportService airportService;

    @Autowired
    public HomeController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("allCitiesWithCountry", airportService.getAllCitiesGroupByCountry());
        model.addAttribute("searchFlightRequest", new SearchFlightRequest());
        return "index";
    }
}
