package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeasingController {

    @GetMapping("/")
    public String OverviewOfLeasings() {
        return "watchLeasingAgreements";
    }

}
