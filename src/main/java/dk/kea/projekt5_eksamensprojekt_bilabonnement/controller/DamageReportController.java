package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
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
                                     @RequestParam("car_id")int car_id)
    {
    DamageReportModel damageReportModel = new DamageReportModel(name,description,employee,car_id);
    damageReportRepository.createNewDamageReport(damageReportModel);
    return "redirect:/DamageReportSite";
    }

    @GetMapping("/UpdateReport/{id}")
    public String updateReportByID(@PathVariable( "id") int reportID, Model model){
        DamageReportModel damageReportModel = damageReportRepository.getDamageReportInDatabaseByID(reportID);
        model.addAttribute("updateReport", damageReportModel);
        return "UpdateReport";}


    @PostMapping("/UpdateReport")
    public String updateReportByID( @RequestParam("id") int id,
                                    @RequestParam("report_name") String report_name,
                                     @RequestParam("report_description") String report_description,
                                     @RequestParam("report_employee_name") String report_employee_name,
                                     @RequestParam("car_id") int car_id){
        DamageReportModel damageReportModel = new DamageReportModel(id, report_name,report_description,report_employee_name,car_id);
        damageReportRepository.UpdateDamageReportEntryInDatabase(damageReportModel);
        return "redirect:DamageReportSite";
    }

    @GetMapping("/deleteFromDamageReportSite/{id}")
    public String deleteCarEntry(@PathVariable("id") int reportId, HttpServletRequest request) {
        damageReportRepository.deletedDamageReportById(reportId);
        String referrer = request.getHeader("referer");
        if (referrer != null && !referrer.isEmpty()) {
            return "redirect:" + referrer;
        } else {
            return "redirect:DamageReportSite";
        }
    }

}
