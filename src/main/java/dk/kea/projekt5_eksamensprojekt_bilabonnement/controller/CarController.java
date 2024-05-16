package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
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
import java.util.Scanner;

/**
 * car controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    CarService carService;

    @Autowired
    DamageReportRepository damageReportRepository;

    @GetMapping("/carListSite")
    public String carListSide(Model model) {
        List<CarModel> carModels = carRepository.getFullListOfCars();
        List<CarModel> carModels1 = carRepository.getSpecificNameFromCars();
        double totalMonthlyPrice = carService.calculateTotalMonthlyPrice();
        model.addAttribute("carlist", carModels);
        model.addAttribute("carModels1", carModels1);
        model.addAttribute("totalMonthlyPrice", totalMonthlyPrice);
        return "carListSite";
    }

    @GetMapping("/ToLeasedCars")
    public String toLeasedCars(Model model) {
        List<CarModel> carModels = carRepository.getLeasedCars();
        double totalMonthlyPriceForLeasedCars = carService.calculateTotalMonthlyPriceForLeasedCars();
        model.addAttribute("leasedCars", carModels);
        model.addAttribute("totalMonthlyPrice", totalMonthlyPriceForLeasedCars);
        return "ToLeasedCars";
    }

    @GetMapping("/ToNonLeasedCars")
    public String toNonLeasedCars(Model model) {
        List<CarModel> carModels = carRepository.getNonLeasedCars();
        double totalMonthlyPriceForNonLeasedCars = carService.calculateTotalMonthlyPriceForNonLeasedCars();
        model.addAttribute("nonLeasedCars", carModels);
        model.addAttribute("totalMonthlyPrice", totalMonthlyPriceForNonLeasedCars);
        return "ToNonLeasedCars";
    }

    @GetMapping("/CreateNewCarEntry")
    public String CreateNewCarEntry() {
        return "/CreateNewCarEntryForm";
    }

    @PostMapping("/CreateNewCarEntryForm")
    public String CreateNewCarEntryForm(
            @RequestParam("car_picture") String car_picture,
            @RequestParam("car_full_name") String car_full_name,
            @RequestParam("car_Serialnr") int car_Serialnr,
            @RequestParam("car_number") int car_number,
            @RequestParam("car_model") String car_model,
            @RequestParam("car_name") String car_name,
            @RequestParam("car_year") String car_year,
            @RequestParam("monthly_price") double monthly_price,
            @RequestParam(value = "is_leased", defaultValue = "false") boolean is_leased,
            @RequestParam("car_description") String car_description
            ) {
        CarModel carModel = new CarModel(car_picture, car_full_name, car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased, car_description);
        carRepository.createNewCarEntry(carModel);
        return "redirect:carListSite";
    }

    @GetMapping("/deleteFromCarList/{id}")
    public String deleteCarEntry(@PathVariable("id") int carId, HttpServletRequest request) {
        carRepository.deleteFromListOfCars(carId);
        String referrer = request.getHeader("referer");
        if (referrer != null && !referrer.isEmpty()) {
            return "redirect:" + referrer;
        } else {
            return "redirect:carListSite";
        }
    }

    @GetMapping("/updateCarEntry/{id}")
    public String updateCarEntryById(@PathVariable("id") int carId, Model model) {
        CarModel carModel = carRepository.GetCarByIdForUpdate(carId);
        model.addAttribute("updateCar", carModel);
        return "updateCarForm";
    }

    @PostMapping("/updateCarEntry")
    public String updateCarEntry(
        @RequestParam("id") int id,
        @RequestParam("car_picture") String car_picture,
        @RequestParam("car_full_name") String car_full_name,
        @RequestParam("car_Serialnr") int car_Serialnr,
        @RequestParam("car_number") int car_number,
        @RequestParam("car_model") String car_model,
        @RequestParam("car_name") String car_name,
        @RequestParam("car_year") String car_year,
        @RequestParam("monthly_price") double monthly_price,
        @RequestParam("is_leased") boolean is_leased,
        @RequestParam("car_description") String car_description
    ) {
        CarModel carModel = new CarModel(id, car_picture, car_full_name, car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased, car_description);
        carRepository.UpdateCarEntryInDatabase(carModel);
        return "redirect:carListSite";
    }

    @GetMapping("/CarInfoPage/{id}")
    public String carInfoPage(
            @PathVariable("id") int car_Id,
            Model model
    ) {
        CarModel carModel = carRepository.GetCarById(car_Id);
        model.addAttribute("carModel", carModel);
        List<DamageReportModel> damageReportModels = damageReportRepository.getAllReportsByCarId(car_Id);
        model.addAttribute("damageReport", damageReportModels);
        return "carInfoPage";
    }
}
