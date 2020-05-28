package pl.mswierczewski.skyfly.models;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    List<Flight> flightsInCart = new ArrayList<>();

    public void addToCart(Flight flight){
        if(!flightsInCart.contains(flight)){
            flightsInCart.add(flight);
        }
    }

    public List<Flight> getFlightsInCart() {
        return flightsInCart;
    }
}
