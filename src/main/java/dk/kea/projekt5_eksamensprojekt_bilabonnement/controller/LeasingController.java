package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.LeasingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
/**
 * leasing controller class
 *
 * @author Viktor Rasmussen, vira0004@stud.kea
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
@Controller
public class LeasingController {

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    LeasingService leasingService;

    //her bliver lavet en metode som viser alle leasinger
    @GetMapping("/watchLeasingAgreements")
    public String OverviewOfLeasings(Model model) {
        List<LeasingModel>allLeasing =leasingRepository.getListOfLeasingContracts();
        model.addAttribute("LeasingAgreements",allLeasing);
        return "watchLeasingAgreements";
    }
    
    @GetMapping("/makeNewLeasing/{id}")
    public String makeNewLeasing(@PathVariable("id") int car_id, Model model){
        CarModel carModel = carRepository.GetCarById(car_id);
        model.addAttribute("car_id", carModel);
        return "makeNewLeasing";
    }

    @PostMapping("/makeNewLeasing")
    public String newLeasingMade(
            @RequestParam("Pris") double monthly_price,
            @RequestParam("startdato") LocalDate start_leasing,
            @RequestParam("slutdato") LocalDate end_leasing,
            @RequestParam("navn") String customer_name,
            @RequestParam(value = "is_unlimited", defaultValue = "false") boolean is_unlimited,
            @RequestParam(value = "is_limited", defaultValue = "false") boolean is_limited,
            @RequestParam("car_id") int car_id,
            @RequestParam("ansat") String employee_name,
            Model model){

        String error = leasingService.checkLeasingDate(start_leasing, end_leasing, is_unlimited, is_limited);

        // Se om der er en fejlmeddelelse fra checkLeasingDate metoden i leasingservice.
        if(error != null) {
            CarModel carModel = carRepository.GetCarById(car_id);
            model.addAttribute("car_id", carModel);
            model.addAttribute("error", error);
            return "makeNewLeasing";
        }

        //Hvis ikke der er en fejlmeddelelse, fortsætter vi med at lave en leasingkontrakt.
        LeasingModel leasingModel = new LeasingModel(employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id);
        leasingRepository.createLeasingContract(leasingModel);
        return "redirect:/CarInfoPage/" + car_id;
    }

    @GetMapping("/deleteFromLeasingList/{id}")
    public String deleteLeasingEntry(@PathVariable("id") int LeasingId, HttpServletRequest request) {
        leasingRepository.deleteLeasing(LeasingId);
        String referrer = request.getHeader("referer");
        if (referrer != null && !referrer.isEmpty()) {
            return "redirect:" + referrer;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/updateLeasingEntry/{id}")
    public String updateLeasingEntryById(@PathVariable("id") int leasingId, Model model) {
        LeasingModel leasingModel = leasingRepository.findById(leasingId);
        model.addAttribute("updateLease", leasingModel);
        return "updateLeasingForm";
    }

    @PostMapping("/updateLeasingEntry")
    public String updateLeasingEntry(
            @RequestParam("id") int id,
            @RequestParam("employee_name") String employee_name,
            @RequestParam("monthly_price") double monthly_price,
            @RequestParam("customer_name") String customer_name,
            @RequestParam("start_leasing") LocalDate start_leasing,
            @RequestParam("end_leasing") LocalDate end_leasing,
            @RequestParam("is_unlimited") boolean is_unlimited,
            @RequestParam("is_limited") boolean is_limited,
            @RequestParam("car_id") int car_id
    ) {

        LeasingModel leasingModel = new LeasingModel(id, employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id);
        leasingRepository.updateLeasing(leasingModel);
        return "redirect:watchLeasingAgreements";
    }
}