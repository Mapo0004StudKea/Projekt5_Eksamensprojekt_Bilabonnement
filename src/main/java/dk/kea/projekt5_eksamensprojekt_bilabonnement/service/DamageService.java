package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {
    @Autowired
    DamageRepository damageRepository;

    public double calculateTotalDamagePrice(){
        List<DamageModel> damage = damageRepository.findAllDamage();
        double totalPrice = 0;
        for(DamageModel damageModel : damage){
            totalPrice += damageModel.getDamage_price();
        }
        return totalPrice;
    }

}
