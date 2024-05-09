package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class LeasingController {
    LeasingRepository leasingRepository;

    @GetMapping("/watchLeasingAgreements")
    public String OverviewOfLeasings() {
        return "watchLeasingAgreements";
    }

    @GetMapping("/makeNewLeasing")
    public String makeNewLeasing(){
        return "makeNewLeasing";
    }

    @PostMapping("/makeNewLeasing")
    public String newLeasingMade(
            @RequestParam("")){

        double leasingPrice;
        LocalDate startLeasing;
        LocalDate endLeasing;
        String customerName;

        boolean isLimited;

        boolean isUnlimited;

        int carId

        return "redirect:/watchLeasingAgreements";
    }
}
