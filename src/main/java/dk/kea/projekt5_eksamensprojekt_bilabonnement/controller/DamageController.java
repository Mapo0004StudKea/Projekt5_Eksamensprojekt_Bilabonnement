package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DamageController {

    @Autowired
    DamageRepository damageRepository;


    @GetMapping("/watchDamages/{id}")
    public String overviewOfDamages(@PathVariable("id") int reportID, Model model) {
        List<DamageModel> damages = damageRepository.findAllDamage();
        model.addAttribute("damageOverview", damages);
        return "watchDamages";
    }

    @GetMapping("makeNewDamage/{id}")
        public String createNewDamage(@PathVariable("id") int damageReport_id, Model model) {
        model.addAttribute("damagereportid", damageReport_id);
        return "makeNewDamage";

    }

    @PostMapping("/makeNewDamage")
    public String createNewDamagePost(
            @RequestParam("damage_name") String damagename,
            @RequestParam("damage_price") double damageprice,
            @RequestParam("damage_description") String damageDescription,
            @RequestParam("damageReport_id") int damageReport_id)

    {
        DamageModel damageModel = new DamageModel(damagename, damageprice, damageDescription, damageReport_id);
        damageRepository.createNewDamage(damageModel);
        return "redirect:watchDamages/" + damageReport_id;

    }

}
