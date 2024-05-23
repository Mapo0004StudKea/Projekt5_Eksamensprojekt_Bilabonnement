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

    @GetMapping("/")
    public String dash() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){return "login";}

    @PostMapping("/login")
    public String login(){ return "redirect:/dashboard";}

    @GetMapping("/dashboard")
    public String dashboard (Model model){
        //her henter vi listen af biler. som er tæt på end_leasing, inden for 30 dage.
        CarsAndLeasingModels result = dashService.listOfCarsNearEndLeasing(30);
        List<CarModel> carsNearEndLeasing = result.getCars();
        List<LeasingModel> leasingModels = result.getLeasingModels();
        model.addAttribute("endLeasing",carsNearEndLeasing);
        model.addAttribute("leasingmodels",leasingModels);

        //Metode der displayer antal biler med en int. Ved hjælp af size() kan vi omforme listen til integer.
        int totalnumberOfCars = carRepository.getFullListOfCars().size();
        model.addAttribute("carsByNumber", totalnumberOfCars);
        int totalNumberOfLeasedCars = carRepository.getLeasedCars().size();
        model.addAttribute("LeasedCars", totalNumberOfLeasedCars);
        int totalNumberOfNonLeasedCars = carRepository.getNonLeasedCars().size();
        model.addAttribute("NonLeasedCars", totalNumberOfNonLeasedCars);
        double totalMonthlyPrice = carService.calculateTotalMonthlyPrice();
        model.addAttribute("totalMonthlyPrice", totalMonthlyPrice);
        double totalMonthlyPriceForLeasedCars = carService.calculateTotalMonthlyPriceForLeasedCars();
        model.addAttribute("totalMonthlyPriceLease", totalMonthlyPriceForLeasedCars);
        double totalMonthlyPriceForNonLeasedCars = carService.calculateTotalMonthlyPriceForNonLeasedCars();
        model.addAttribute("totalMonthlyPriceNonLease", totalMonthlyPriceForNonLeasedCars);
        int totalNumberOfLeasingAgreements = leasingRepository.getListOfLeasingContracts().size();
        model.addAttribute("LeasingAgreementsByNumbers", totalNumberOfLeasingAgreements);
        int totalNumbersOfDamageReports = damageReportRepository.getFullListOfReport().size();
        model.addAttribute("NumberOfDamageReports", totalNumbersOfDamageReports);
        int totalNumberOfDamages = damageRepository.findAllDamage().size();
        model.addAttribute("TotalNumberOfDamages", totalNumberOfDamages);
        double totalSumOfDamages = damageRepository.getTotalPrice();
        model.addAttribute("TotalSumOfDamages", totalSumOfDamages);
        double mostExpensiveDamage = damageRepository.getMostExpensiveDamage();
        model.addAttribute("MostExpensiveDamage", mostExpensiveDamage);
        double cheapestDamage = damageRepository.getCheapestDamage();
        model.addAttribute("CheapestDamage", cheapestDamage);
        int carWithMostDamageReports = damageReportRepository.getCarWithMostDamageReports();
        model.addAttribute("CarWithMostDamageReport", carWithMostDamageReports);
        int carWithMostDamageReportsByCount = damageReportRepository.getCarWithMostDamageReportsByCount();
        model.addAttribute("test", carWithMostDamageReportsByCount);
        int carWithMostLeasingAgreements = leasingRepository.getCarWithMostLeasingAgreements();
        model.addAttribute("CarWithMostLeasingAgreements", carWithMostLeasingAgreements);
        int carWithMostLeasingAgreementsByCount = leasingRepository.getCarWithMostLeasingAgreementsByCount();
        model.addAttribute("CarWithMostLeasingAgreementsByCount", carWithMostLeasingAgreementsByCount);
        return "dashboard";
    }
}
