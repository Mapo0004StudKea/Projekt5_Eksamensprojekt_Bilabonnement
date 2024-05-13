package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LeasingController {

    @Autowired
    LeasingRepository leasingRepository;

    //her bliver lavet en metode som viser alle leasinger
    @GetMapping("/watchLeasingAgreements")
    public String OverviewOfLeasings(Model model) {
        List<LeasingModel>allLeasing =leasingRepository.getListOfLeasingContracts();
        model.addAttribute("LeasingAgremenst",allLeasing);
        return "watchLeasingAgreements";
    }


    @GetMapping("/makeNewLeasing")
    public String makeNewLeasing(){
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
            @RequestParam("carId") int car_id,
            @RequestParam("ansat") String employee_name){

        LeasingModel leasingModel =new LeasingModel(employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id);
        leasingRepository.createLeasingContract(leasingModel);

        return "redirect:/watchLeasingAgreements";
    }
}
