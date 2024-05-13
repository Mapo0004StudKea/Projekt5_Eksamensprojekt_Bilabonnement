package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LeasingRepository leasingRepository;

    @GetMapping("/carListSide")
    public String carListSide(Model model) {
        List<CarModel> carModels = carRepository.getFullListOfCars();
        model.addAttribute("carlist", carModels);
        return "carListSide";
    }

    @GetMapping("/CreateNewCarEntry")
    public String CreateNewCarEntry() {
        return "/CreateNewCarEntryForm";
    }

    @PostMapping("/CreateNewCarEntryForm")
    public String CreateNewCarEntryForm(
            @RequestParam("car_Serialnr") int car_Serialnr,
            @RequestParam("car_number") int car_number,
            @RequestParam("car_model") String car_model,
            @RequestParam("car_name") String car_name,
            @RequestParam("car_year") String car_year,
            @RequestParam("monthly_price") double monthly_price,
            @RequestParam("is_leased") boolean is_leased
    ) {
        CarModel carModel = new CarModel(car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased);
        carRepository.createNewNewCarEntry(carModel);
        return "redirect:carListSide";
    }

    @GetMapping("/deleteFromCarList/{id}")
    public String deleteCarEntry(@PathVariable("id") int carId) {
        carRepository.deleteFromListOfCars(carId);
        return "/carListSide";
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
        @RequestParam("car_Serialnr") int car_Serialnr,
        @RequestParam("car_number") int car_number,
        @RequestParam("car_model") String car_model,
        @RequestParam("car_name") String car_name,
        @RequestParam("car_year") String car_year,
        @RequestParam("monthly_price") double monthly_price,
        @RequestParam("is_leased") boolean is_leased
    ) {
        CarModel carModel = new CarModel(id, car_Serialnr, car_number, car_model, car_name, car_year, monthly_price, is_leased);
        carRepository.UpdateCarEntryInDatabase(carModel);
        return "redirect:carListSide";
    }
}