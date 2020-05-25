package pl.mswierczewski.skyfly.controllers.web;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministrationPanelController {

    @GetMapping("/administration-panel")
    public String administrationPanel(Model model){
        return "administrationPanel";
    }
}
