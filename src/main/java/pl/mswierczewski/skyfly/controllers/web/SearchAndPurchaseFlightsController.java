package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.mswierczewski.skyfly.dtos.FlightSummaryResponse;
import pl.mswierczewski.skyfly.dtos.SearchFlightRequest;
import pl.mswierczewski.skyfly.dtos.SearchFlightResponse;
import pl.mswierczewski.skyfly.dtos.SelectedFlights;
import pl.mswierczewski.skyfly.models.Flight;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.models.SkyFlyUser;
import pl.mswierczewski.skyfly.services.AirportService;
import pl.mswierczewski.skyfly.services.FlightService;
import pl.mswierczewski.skyfly.services.TicketService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"numberOfAdditionalPassengers","passengers", "flights", "user"})
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SearchAndPurchaseFlightsController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final TicketService ticketService;

    @Autowired
    public SearchAndPurchaseFlightsController(FlightService flightService, AirportService airportService,
                                              TicketService ticketService){
        this.flightService = flightService;
        this.airportService = airportService;
        this.ticketService = ticketService;
    }

    @PostMapping("/searchFlights")
    public RedirectView searchFlights(Model model, RedirectAttributes redirectAttributes,
                                      @ModelAttribute(value = "searchFlightRequest") SearchFlightRequest searchFlightRequest){

        List<SearchFlightResponse> departureFlights = flightService.getMatchingFlights(searchFlightRequest);
        redirectAttributes.addFlashAttribute("departureFlights", departureFlights);

        if (!searchFlightRequest.isInOneWay()) {
            List<SearchFlightResponse> arrivalFlights = flightService.getMatchingFlights(searchFlightRequest.reverseDirectionAndDate());
            redirectAttributes.addFlashAttribute("arrivalFlights", arrivalFlights);
        }

        model.addAttribute("numberOfAdditionalPassengers", searchFlightRequest.getNumberOfPassenger() - 1);

        return new RedirectView("/flights", true);
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/flights")
    public String showFoundFlights(Model model){
        List<SearchFlightResponse> departureFlights = (List<SearchFlightResponse>) model.asMap().get("departureFlights");
        List<SearchFlightResponse> arrivalFlights = (List<SearchFlightResponse>) model.asMap().get("arrivalFlights");

        model.addAttribute("searchFlightRequest", new SearchFlightRequest());
        model.addAttribute("allCitiesWithCountry", airportService.getAllCitiesGroupByCountry());
        model.addAttribute("departureFlights", departureFlights);
        model.addAttribute("arrivalFlights", arrivalFlights);
        model.addAttribute("selectedFlights", new SelectedFlights());

        return "flights";
    }

    @PostMapping("/selectFlights")
    public String selectFlights(Model model,
                                @ModelAttribute(value = "selectedFlights") SelectedFlights selectedFlights){

        var flights = flightService.getFlightsByIds(selectedFlights.getAllFlightsIds());
        model.addAttribute("flights", flights);

        return "redirect:/user-info";
    }

    @GetMapping("/user-info")
    public String getUserInfo(Authentication authentication, Model model){
        SkyFlyUser user = (SkyFlyUser) authentication.getPrincipal();

        model.addAttribute("user", user);

        return "userInfo";
    }

    @GetMapping("/confirmPersonalData")
    public String confirmUserInfo(Model model, @SessionAttribute(value = "numberOfAdditionalPassengers") Integer numOfPassengers){
        model.addAttribute("passengers", new HashMap<Integer, Passenger>());

        if (numOfPassengers > 0){
            return "redirect:/add-passenger-info?id=" + 1;
        }

        return "redirect:/summary";
    }

    @GetMapping("/add-passenger-info")
    public String getPassengerInfoForm(Model model, @RequestParam(value = "id") Integer passengerId){
        model.addAttribute("passengerId", passengerId);
        Passenger p = new Passenger();
        p.setId(passengerId.longValue());
        model.addAttribute("passenger", p);

        return "passengerInfoForm";
    }

    @PostMapping("/addPassenger")
    public String addPassenger(@Valid @ModelAttribute(value = "passenger") Passenger passenger,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @RequestParam(value = "id") Integer passengerId,
                               @SessionAttribute(value = "numberOfAdditionalPassengers") Integer numOfPassengers,
                               @SessionAttribute(value = "passengers") Map<Integer, Passenger> passengers){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.passenger", bindingResult);
            redirectAttributes.addFlashAttribute("passenger", passenger);
            return "redirect:/add-passenger-info?id=" + passengerId;
        }

        passenger.setId(null);
        passengers.put(passengerId, passenger);
        int id = passengerId;

        if(++id <= numOfPassengers){
            return "redirect:/add-passenger-info?id=" + id;
        } else {
            return "redirect:/summary";
        }
    }

    @GetMapping("/summary")
    public String summary(Model model,
                          @SessionAttribute(value = "user") SkyFlyUser user,
                          @SessionAttribute(value = "flights") List<Flight> flights,
                          @SessionAttribute(value = "passengers") Map<Integer, Passenger> passengers){

        List<Passenger> allPassengers = new ArrayList<>(passengers.values());
        Passenger userPassenger = (Passenger) user.getUserDetails();
        allPassengers.add(userPassenger);

        List<FlightSummaryResponse> flightSummaryResponses = new ArrayList<>();

        flights.forEach(
                flight -> flightSummaryResponses.add(new FlightSummaryResponse(flight, allPassengers))
        );

        model.addAttribute("passengers", allPassengers);
        model.addAttribute("flightSummaryResponses", flightSummaryResponses);

        return "summary";
    }

    @GetMapping("/buyTickets")
    public String buyTickets(@SessionAttribute(value = "flights") List<Flight> flights,
                             @SessionAttribute(value = "passengers") List<Passenger> passengers){
        boolean result = true;

        try {
            ticketService.generateTicketsAndSave(flights, passengers);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        if (result){
            return "buySuccess";
        } else {
            return "buyError";
        }
    }


}
