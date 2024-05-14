package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * damageReport controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 */

@Controller
public class DamageReportController {

    @Autowired
    DamageReportRepository damageReportRepository;

    @GetMapping("/DamageReportSite")
    public String viewDamageReport(Model model) {
        List<DamageReportModel> allDamageReports = damageReportRepository.getFullListOfReport();
        model.addAttribute("damageReport",allDamageReports);
        return "DamageReportSite";
    }

    @GetMapping("/CreateNewReportEntry")
    public String CreateNewReportEntry(){
        return "CreateNewReportEntry";}

    @PostMapping("/CreateNewReportEntry")
    public String MakeNewReportEntry(@RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("employee") String employee,
                                     @RequestParam("car_id") int car_id)
    {

    DamageReportModel damageReportModel = new DamageReportModel(name,description,employee, car_id);
    damageReportRepository.createNewDamageReport(damageReportModel);
    return "redirect:/DamageReportSite";
    }

}
