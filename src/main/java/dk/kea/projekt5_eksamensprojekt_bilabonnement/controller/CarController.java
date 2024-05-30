package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * car controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 *
 * Bliver brugt til at vise alle vores bil-html sider
 */
@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    DamageReportRepository damageReportRepository;

    @Autowired
    CarService carService;

    //En getMapping henter alle bilmodeller og viser dem på carListSite-siden
    @GetMapping("/carListSite")
    public String carListSide(Model model) {
        //Henter en liste af alle biler fra databasen
        List<CarModel> carModels = carRepository.getFullListOfCars();
        //Udregner den totale månedlige pris for alle biler
        double totalMonthlyPrice = carService.calculateTotalMonthlyPrice();
        //tilføjer listen af biler til modellen så den kan blive brugt fra vores html
        model.addAttribute("carlist", carModels);
        //tilføjer den total månedlige pris så den kan blive brugt fra vores html
        model.addAttribute("totalMonthlyPrice", totalMonthlyPrice);
        //returnere carListSite-siden
        return "carListSite";
    }

    //En getMapping der henter alle leasede biler og viser dem på ToLeasedCars-siden
    @GetMapping("/ToLeasedCars")
    public String toLeasedCars(Model model) {
        //Henter en liste af alle biler fra databasen
        List<CarModel> carModels = carRepository.getLeasedCars();
        // Beregner den samlede månedlige pris for leasede biler
        double totalMonthlyPriceForLeasedCars = carService.calculateTotalMonthlyPriceForLeasedCars();
        // Tilføjer leasede bilers liste til modellen for at kunne vise det på siden
        model.addAttribute("leasedCars", carModels);
        // Tilføjer den samlede månedlige pris for leasede biler til modellen
        model.addAttribute("totalMonthlyPrice", totalMonthlyPriceForLeasedCars);
        //returnere ToLeasedCars-siden
        return "ToLeasedCars";
    }

    //En getMapping der henter alle ikke-leasede biler og viser dem på ToNonLeasedCars-siden
    @GetMapping("/ToNonLeasedCars")
    public String toNonLeasedCars(Model model) {
        // Henter en liste af ikke-leasede biler fra databasen
        List<CarModel> carModels = carRepository.getNonLeasedCars();
        // Beregner den samlede månedlige pris for ikke-leasede biler
        double totalMonthlyPriceForNonLeasedCars = carService.calculateTotalMonthlyPriceForNonLeasedCars();
        // Tilføjer ikke-leasede bilers liste til modellen for at kunne vise det på siden
        model.addAttribute("nonLeasedCars", carModels);
        // Tilføjer den samlede månedlige pris for ikke-leasede biler til modellen
        model.addAttribute("totalMonthlyPrice", totalMonthlyPriceForNonLeasedCars);
        //returnere ToNonLeasedCars-siden
        return "ToNonLeasedCars";
    }

    //En getMapping der viser siden til at oprette nye biler
    @GetMapping("/CreateNewCarEntry")
    public String CreateNewCarEntry() {
        //returnere CreateNewCarEntryForm-siden
        return "CreateNewCarEntryForm";
    }

    //En postMapping der håndterer oprettelsen af nye biler
    @PostMapping("/CreateNewCarEntryForm")
    public String CreateNewCarEntryForm(
            @RequestParam("car_picture") String car_picture,
            @RequestParam("car_full_name") String car_full_name,
            @RequestParam("car_Serialnr") String  car_Serialnr,
            @RequestParam("car_number") String  car_number,
            @RequestParam("car_model") String car_model,
            @RequestParam("car_name") String car_name,
            @RequestParam("car_year") String car_year,
            @RequestParam("monthly_price") double monthly_price,
            @RequestParam(value = "is_leased", defaultValue = "false") boolean is_leased,
            @RequestParam("car_description") String car_description
            ) {
        //Opretter en ny CarModel instans med de indsendte data
        CarModel carModel = new CarModel(car_picture, car_full_name, car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased, car_description);
        //Gemmer den nye bil i databasen
        carRepository.createNewCarEntry(carModel);
        //Her bliver man omdirigeret til carListSite-siden
        return "redirect:carListSite";
    }

    //en getMapping der sletter en bil baseret på dens id
    @GetMapping("/deleteFromCarList/{id}")
    public String deleteCarEntry(@PathVariable("id") int carId, HttpServletRequest request) {
        //Sletter bilen fra databasen
        carRepository.deleteFromListOfCars(carId);
        //Henter URL'en til den forrige side
        String referrer = request.getHeader("referer");
        //Omdirigerer til den forrige side, hvis den findes, ellers til carListSite
        if (referrer != null && !referrer.isEmpty()) {
            return "redirect:" + referrer;
        } else {
            return "redirect:carListSite";
        }
    }

    //En getMapping der hHenter data til opdatering af biler baseret på dens id
    @GetMapping("/updateCarEntry/{id}")
    public String updateCarEntryById(@PathVariable("id") int carId, Model model) {
        //Henter bilen fra databasen
        CarModel carModel = carRepository.GetCarByIdForUpdate(carId);
        //Tilføjer bilen til modellen for at kunne vise det på siden
        model.addAttribute("updateCar", carModel);
        //returnere updateCarForm-siden
        return "updateCarForm";
    }

    //en postMapping der håndterer opdateringen af en bil
    @PostMapping("/updateCarEntry")
    public String updateCarEntry(
        @RequestParam("id") int id,
        @RequestParam("car_picture") String car_picture,
        @RequestParam("car_full_name") String car_full_name,
        @RequestParam("car_Serialnr") String  car_Serialnr,
        @RequestParam("car_number") String  car_number,
        @RequestParam("car_model") String car_model,
        @RequestParam("car_name") String car_name,
        @RequestParam("car_year") String car_year,
        @RequestParam("monthly_price") double monthly_price,
        @RequestParam("is_leased") boolean is_leased,
        @RequestParam("car_description") String car_description
    ) {
        //Opretter en ny CarModel instans med de opdaterede data
        CarModel carModel = new CarModel(id, car_picture, car_full_name, car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased, car_description);
        // Opdaterer bilen i databasen
        carRepository.UpdateCarEntryInDatabase(carModel);
        //Omdirigeres til carListSite-siden
        return "redirect:carListSite";
    }

    //En getMapping der viser detaljer om en bil baseret på dens id
    @GetMapping("/CarInfoPage/{id}")
    public String carInfoPage(
            @PathVariable("id") int car_Id,
            Model model
    ) {
        //Henter bilen fra databasen
        CarModel carModel = carRepository.GetCarById(car_Id);
        //Tilføjer bilen til modellen for at kunne vise det på siden
        model.addAttribute("carModel", carModel);
        //Henter alle skaderapporter for bilen
        List<DamageReportModel> damageReportModels = damageReportRepository.getAllReportsByCarId(car_Id);
        //Tilføjer skaderapporterne til modellen
        model.addAttribute("damageReport", damageReportModels);
        //Henter alle leasingaftaler for bilen
        List<LeasingModel> leasingModels = leasingRepository.getListOfLeasingContractsById(car_Id);
        //Tilføjer leasingaftalerne til modellen
        model.addAttribute("leaseReport", leasingModels);
        //returnere carInfoPage-siden
        return "carInfoPage";
    }

}
