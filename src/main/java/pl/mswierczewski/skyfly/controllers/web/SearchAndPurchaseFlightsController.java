package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.mswierczewski.skyfly.dtos.FlightRequest;
import pl.mswierczewski.skyfly.dtos.FlightResponse;
import pl.mswierczewski.skyfly.models.Cart;
import pl.mswierczewski.skyfly.services.FlightService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes({"flights", "cart"})
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SearchAndPurchaseFlightsController {

    private final FlightService flightService;

    @Autowired
    public SearchAndPurchaseFlightsController(FlightService flightService){
        this.flightService = flightService;
    }

    @PostMapping("/searchFlights")
    public String searchFlights(Model model,
                                @ModelAttribute(value = "flightRequest") FlightRequest flightRequest){
        var flights = flightService.getFlights(flightRequest);
        model.addAttribute("flights", flights);
        return "redirect:/flights";
    }

    @GetMapping("/flights")
    public String showFoundFlights(Model model){
        if (!model.containsAttribute("cart")){
            model.addAttribute("cart", new Cart());
        }
        return "flights";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("flight_id") Long id, @SessionAttribute(value = "cart") Cart cart){
        cart.addToCart(flightService.getFlightById(id));
        System.out.println("=======================================================================");
        cart.getFlightsInCart().forEach(System.out::println);

        return "redirect:/flights";
    }

}
