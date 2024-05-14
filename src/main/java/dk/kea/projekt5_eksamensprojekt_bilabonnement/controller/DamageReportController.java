package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * damageReport controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 */

@Controller
public class DamageReportController {

    @GetMapping("/DamageReportSite")
    public String viewDamageReport() {
        return "DamageReportSite";
    }
}
