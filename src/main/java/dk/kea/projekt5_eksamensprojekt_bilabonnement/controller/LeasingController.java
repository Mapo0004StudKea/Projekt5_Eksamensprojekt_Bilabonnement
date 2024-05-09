package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
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
            @RequestParam("employe") String employee_name,
            @RequestParam("mothlyPrice") double monthly_price,
            @RequestParam("customer") String customer_name,
            @RequestParam("start_leasing") LocalDate start_leasing,
            @RequestParam("end_leasing") LocalDate end_leasing,
            @RequestParam("is_unlimited") boolean is_unlimited,
            @RequestParam("is_limited") boolean is_limited,
            @RequestParam("car_id") int car_id ) {

        LeasingModel leasingModel =new LeasingModel(monthly_price,start_leasing,end_leasing,customer_name,is_unlimited,is_limited,car_id,employee_name);
        leasingRepository.leasingNewContrakt(leasingModel);


        return "redirect:/watchLeasingAgreements";
    }
}
