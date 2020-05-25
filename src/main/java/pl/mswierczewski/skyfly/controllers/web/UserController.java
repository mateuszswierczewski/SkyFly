package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mswierczewski.skyfly.models.ContactDetails;
import pl.mswierczewski.skyfly.models.Passenger;
import pl.mswierczewski.skyfly.security.user.SkyFlyUser;
import pl.mswierczewski.skyfly.security.user.DefaultSkyFlyUserService;
import pl.mswierczewski.skyfly.security.user.SkyFlyUserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final SkyFlyUserService userService;

    @Autowired
    public UserController(DefaultSkyFlyUserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user", new SkyFlyUser());
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("contact", new ContactDetails());
        return "/user_management_templates/registerForm";
    }

    @PostMapping("/register")
    public String registerUserAsPassenger(@Valid @ModelAttribute(value = "user") SkyFlyUser user, BindingResult userBindingResult,
                                          @Valid @ModelAttribute(value = "passenger") Passenger passenger, BindingResult passengerBindingResult,
                                          @Valid @ModelAttribute(value = "contact") ContactDetails contactDetails, BindingResult contactBindingResult){
        if(userBindingResult.hasErrors() || passengerBindingResult.hasErrors() || contactBindingResult.hasErrors()){
            return "/user_management_templates/registerForm";
        }

        userService.registerUserAsPassenger(user, passenger, contactDetails);
        return "/user_management_templates/loginForm";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/user_management_templates/loginForm";
    }

}
