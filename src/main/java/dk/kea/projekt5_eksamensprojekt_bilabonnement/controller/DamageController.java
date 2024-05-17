package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
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
 * Damages controller class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * */

@Controller
public class DamageController {

    @Autowired
    DamageRepository damageRepository;


    @GetMapping("/watchDamages")
    public String overviewOfDamages(Model model) {
        List<DamageModel> damages = damageRepository.findAllDamage();
        model.addAttribute("damageOverview", damages);
        return "watchDamages";
    }

    @GetMapping("/UpdateDamage/{id}")
    public String updateDamageById(@PathVariable("id") int damage_id, Model model) {
        DamageModel damageModel = damageRepository.updateDamageById(damage_id);
        model.addAttribute("updateDamage", damageModel);
        return "UpdateDamage";
    }


    @PostMapping("/UpdateDamage")
    public String updateDamage(
            @RequestParam("id") int id,
            @RequestParam("damage_name") String damage_name,
            @RequestParam("damage_price") double damage_price,
            @RequestParam("damage_description") String damage_description,
            @RequestParam("damageReport_id") int damageReport_id)
    {
        DamageModel damageModel = new DamageModel(id, damage_name, damage_price, damage_description, damageReport_id);
        damageRepository.updateDamageDatabase(damageModel);
        return "redirect:watchDamages";
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
        return "redirect:watchDamageReport/" + damageReport_id;

    }
    @GetMapping("/deleteDamage/{id}")
    public String deleteDamage(@PathVariable("id") int damageReport_id, HttpServletRequest request) {
        damageRepository.deleteDamage(damageReport_id);
        String referrer = request.getHeader("referer");
        if (referrer != null && !referrer.isEmpty()) {
            return "redirect:" + referrer;
        } else{return "redirect:watchDamageReport/";}
    }

}
