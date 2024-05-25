package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarsAndLeasingModels;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.CarService;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.DashService;
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
 * @author Viktor Rasmussen, vira0004@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 *
 * Side der bliver brugt til at vise dashboard'et samt alt information på siden.
 * */

@Controller
public class DashController {

   @Autowired
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    DamageReportRepository damageReportRepository;

    @Autowired
    DamageRepository damageRepository;
    @Autowired
    DashService dashService;

    //Håndterer getMapping til rod-URL
    @GetMapping("/")
    public String dash() {
        //Omdirigeres til login-siden
        return "redirect:/login";
    }

    //Håndterer getMapping til login-siden
    @GetMapping("/login")
    public String loginPage(){
        //returnerer login-skabelonen
        return "login";
    }

    //Håndterer portMapping til login-siden
    @PostMapping("/login")
    public String login(){
        //omdirigeres til dashboard-siden
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard (Model model){
        //Henter listen af biler der er tæt på leasingens afslutning inden for 30 dage
        CarsAndLeasingModels result = dashService.listOfCarsNearEndLeasing(30);
        List<CarModel> carsNearEndLeasing = result.getCars();
        List<LeasingModel> leasingModels = result.getLeasingModels();
        //Tilføjer listen af biler tæt på endt leasing til modellen
        model.addAttribute("endLeasing", carsNearEndLeasing);
        //Tilføjer listen af leasingmodeller til modellen
        model.addAttribute("leasingmodels", leasingModels);
        //Henter og tilføjer det samlede antal biler til modellen
        int totalNumberOfCars = carRepository.getFullListOfCars().size();
        model.addAttribute("carsByNumber", totalNumberOfCars);
        //Henter og tilføjer det samlede antal leasede biler til modellen
        int totalNumberOfLeasedCars = carRepository.getLeasedCars().size();
        model.addAttribute("LeasedCars", totalNumberOfLeasedCars);
        //Henter og tilføjer det samlede antal ikke-leasede biler til modellen
        int totalNumberOfNonLeasedCars = carRepository.getNonLeasedCars().size();
        model.addAttribute("NonLeasedCars", totalNumberOfNonLeasedCars);
        //Beregner og tilføjer den samlede månedlige pris for alle biler til modellen
        double totalMonthlyPrice = carService.calculateTotalMonthlyPrice();
        model.addAttribute("totalMonthlyPrice", totalMonthlyPrice);
        //Beregner og tilføjer den samlede månedlige pris for leasede biler til modellen
        double totalMonthlyPriceForLeasedCars = carService.calculateTotalMonthlyPriceForLeasedCars();
        model.addAttribute("totalMonthlyPriceLease", totalMonthlyPriceForLeasedCars);
        //Beregner og tilføjer den samlede månedlige pris for ikke-leasede biler til modellen
        double totalMonthlyPriceForNonLeasedCars = carService.calculateTotalMonthlyPriceForNonLeasedCars();
        model.addAttribute("totalMonthlyPriceNonLease", totalMonthlyPriceForNonLeasedCars);
        //Henter og tilføjer det samlede antal leasingaftaler til modellen
        int totalNumberOfLeasingAgreements = leasingRepository.getListOfLeasingContracts().size();
        model.addAttribute("LeasingAgreementsByNumbers", totalNumberOfLeasingAgreements);
        //Henter og tilføjer det samlede antal skaderapporter til modellen
        int totalNumbersOfDamageReports = damageReportRepository.getFullListOfReport().size();
        model.addAttribute("NumberOfDamageReports", totalNumbersOfDamageReports);
        //Henter og tilføjer det samlede antal skader til modellen
        int totalNumberOfDamages = damageRepository.findAllDamage().size();
        model.addAttribute("TotalNumberOfDamages", totalNumberOfDamages);
        //Henter og tilføjer den samlede sum af skader til modellen
        double totalSumOfDamages = damageRepository.getTotalPrice();
        model.addAttribute("TotalSumOfDamages", totalSumOfDamages);
        //Henter og tilføjer den dyreste skade til modellen
        double mostExpensiveDamage = damageRepository.getMostExpensiveDamage();
        model.addAttribute("MostExpensiveDamage", mostExpensiveDamage);
        //Henter og tilføjer den billigste skade til modellen
        double cheapestDamage = damageRepository.getCheapestDamage();
        model.addAttribute("CheapestDamage", cheapestDamage);
        //Henter og tilføjer bilen med flest skaderapporter til modellen
        int carWithMostDamageReports = damageReportRepository.getCarWithMostDamageReports();
        model.addAttribute("CarWithMostDamageReport", carWithMostDamageReports);
        //Henter og tilføjer antal skaderapporter for bilen med flest skaderapporter til modellen
        int carWithMostDamageReportsByCount = damageReportRepository.getCarWithMostDamageReportsByCount();
        model.addAttribute("test", carWithMostDamageReportsByCount);
        //Henter og tilføjer bilen med flest leasingaftaler til modellen
        int carWithMostLeasingAgreements = leasingRepository.getCarWithMostLeasingAgreements();
        model.addAttribute("CarWithMostLeasingAgreements", carWithMostLeasingAgreements);
        //Henter og tilføjer antal leasingaftaler for bilen med flest leasingaftaler til modellen
        int carWithMostLeasingAgreementsByCount = leasingRepository.getCarWithMostLeasingAgreementsByCount();
        model.addAttribute("CarWithMostLeasingAgreementsByCount", carWithMostLeasingAgreementsByCount);
        //Returnerer dashboard-skabelonen
        return "dashboard";
    }
}
