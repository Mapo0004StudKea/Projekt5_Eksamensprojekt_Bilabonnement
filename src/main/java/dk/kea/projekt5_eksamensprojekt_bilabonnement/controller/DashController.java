package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
   @Autowired
    CarRepository carRepository;
//    @Autowired
//    LeasingRepository leasingRepository;
    @Autowired
    CarService carService;


    @GetMapping("/login")
    public String loginPage(){return "login";}

    @PostMapping("/login")
    public String login(){ return "redirect:/dashboard";}

    @GetMapping("/dashboard")
    public String dashboard (Model model){
        //Metode der displayer antal biler med en int. Ved hjælp af size() kan vi omforme listen til integer.
        int totalnumberOfCars = carRepository.getFullListOfCars().size();
        model.addAttribute("carsByNumber", totalnumberOfCars);
        return "dashboard";
    }
}
