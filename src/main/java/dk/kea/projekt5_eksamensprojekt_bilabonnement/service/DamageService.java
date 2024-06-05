package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * leasing service class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
@Service
public class DamageService {
    @Autowired
    DamageRepository damageRepository;


    public double totalPriceForDamages(int damageReport_id) {
        List<DamageModel> damageModels = damageRepository.getFullPriceFromId(damageReport_id);
        double totalPrice = 0;
        for (DamageModel damageModel : damageModels) {
            totalPrice += damageModel.getDamage_price();
        }
        return totalPrice;
    }
}
