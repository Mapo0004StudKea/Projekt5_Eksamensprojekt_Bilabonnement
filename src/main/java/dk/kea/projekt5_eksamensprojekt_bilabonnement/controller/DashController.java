package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Dashboard controller class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * */

@Controller
public class DashController {
//    @Autowired
//    DamageRepository DamageRepository;
//    @Autowired
//    DamageReportRepository DamageReportRepository;
//    @Autowired
//    CarRepository carRepository;
//    @Autowired
//    LeasingRepository leasingRepository;


    @GetMapping("/login")
    public String loginPage(){return "login";}

    @PostMapping("/login")
    public String login(){ return "redirect:/dashboard";}

    @GetMapping("/dashboard")
    public String dashboard (){return "dashboard";}
}
