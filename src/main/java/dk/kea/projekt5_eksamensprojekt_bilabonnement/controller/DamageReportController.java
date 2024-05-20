package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarReportModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageReportRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.service.DamageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

/**
 * damageReport controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 * @author Viktor Rasmussen, vira0004@stud.kea
 */

@Controller
public class DamageReportController {

    @Autowired
    DamageReportRepository damageReportRepository;
    @Autowired
    DamageRepository damageRepository;
    @Autowired
    DamageService damageService;
    @Autowired
    CarRepository carRepository;

    @GetMapping("/DamageReportSite")
    public String viewDamageReport(Model model) {
        //Her henter den alle skadesrapporter fra databasen
        List<DamageReportModel> allDamageReports = damageReportRepository.getFullListOfReport();
        //Her henter den alle biler fra databasen
        List<CarModel> carModels = carRepository.getFullListOfCars();

        //Her bruger vi TreeMap for at bevare rækkefølgen efter bil id'er
        Map<Integer, List<DamageReportModel>> carReportMap = new TreeMap<>();

        //For hver skaderapport i listen af alle skadesrapporter
        for (DamageReportModel report : allDamageReports) {
            //Her bruger vi computeIfAbsent for at sikre, at der er en liste tilknyttet til det givne bil-id
            //Hvis bil-id'et ikke allerede findes i de TreeMap (carReportMap), tilføjer vi en ny ArrayList
            //Derefter tilføjer vi skaderapporten til listen
            carReportMap.computeIfAbsent(report.getCar_id(), k -> new ArrayList<>()).add(report);
        }

        //Her oprettes en liste af CarReport objekter
        List<CarReportModel> carReports = new ArrayList<>();

        //Her kombineres bil-modellen med skaderapport-modellen
        for (CarModel car : carModels) {
            List<DamageReportModel> reports = carReportMap.get(car.getId());
            if (reports != null) {
                for (DamageReportModel report : reports) {
                    carReports.add(new CarReportModel(car, report));
                }
            }
        }

        //Her tilføjes carReports til modelattributten
        model.addAttribute("carReports", carReports);
        //Her returneres navnet på visningen
        return "DamageReportSite";
    }
    @GetMapping("/CreateNewReportEntry/{id}")
    public String CreateNewReportEntry(@PathVariable("id") int car_id, Model model) {
        CarModel carModel = carRepository.GetCarById(car_id);
        model.addAttribute("car_id", carModel);
        return "CreateNewReportEntry";
    }

    @PostMapping("/CreateNewReportEntry")
    public String MakeNewReportEntry(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("employee") String employee,
            @RequestParam("car_id") int car_id,
            @RequestParam("report_Damage_Date")LocalDate report_Damage_Date){
        DamageReportModel damageReportModel = new DamageReportModel(name, description, employee,car_id, report_Damage_Date);
        damageReportRepository.createNewDamageReport(damageReportModel);
        return "redirect:/CarInfoPage/" + car_id;
    }

    @GetMapping("/UpdateReport/{id}")
    public String updateReportByID(@PathVariable("id") int reportID, Model model) {
        DamageReportModel damageReportModel = damageReportRepository.getDamageReportInDatabaseByID(reportID);
        model.addAttribute("updateReport", damageReportModel);
        return "UpdateReport";
    }


    @PostMapping("/UpdateReport")
    public String updateReportByID(@RequestParam("id") int id,
                                   @RequestParam("report_name") String report_name,
                                   @RequestParam("report_description") String report_description,
                                   @RequestParam("report_employee_name") String report_employee_name,
                                   @RequestParam("car_id") int car_id,
                                   @RequestParam("report_Damage_Date")LocalDate report_Damage_Date){
        DamageReportModel damageReportModel = new DamageReportModel(id, report_name, report_description, report_employee_name, car_id, report_Damage_Date);
        damageReportRepository.UpdateDamageReportEntryInDatabase(damageReportModel);
        return "redirect:watchDamageReport/" + id;
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

    @GetMapping("/watchDamageReport/{id}")
    public String watchDamageReport(@PathVariable("id") int id, Model model) {
        DamageReportModel damageReportModel = damageReportRepository.getDamageReportInDatabaseByID(id);
        model.addAttribute("watchdamage", damageReportModel);
        List<DamageModel> damageModelList = damageRepository.findDamageReportById(id);

        model.addAttribute("damage",damageModelList);
        double totalPrice = damageService.totalPriceForDamages(id);
        model.addAttribute("totalPriceForDamages", totalPrice);
        return "watchDamageReport";
    }
}