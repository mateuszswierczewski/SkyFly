package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mswierczewski.skyfly.security.user.SkyFlyUser;
import pl.mswierczewski.skyfly.security.user.SkyFlyUserService;

@Controller
public class UserController {

    private SkyFlyUserService userService;

    @Autowired
    public UserController(SkyFlyUserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user", new SkyFlyUser());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerUserAsPassenger(@ModelAttribute SkyFlyUser user){
        userService.registerUserAsPassenger(user);
        return "registerSuccess";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/user_management_templates/loginForm";
    }

}
