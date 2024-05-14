package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * leasing service class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 */

@Service
public class LeasingService {

    @Autowired
    private LeasingRepository leasingRepository;

    //Metode til at beregne forskellen mellem limited og unlimited.
    public double calculateLeasingPrice (boolean is_limited, boolean is_unlimited, double monthly_price){
        double totalPrice = 0;

        if(is_limited){
            totalPrice = monthly_price + 500; //Tillæg for limited
        } else if(is_unlimited){
            totalPrice = monthly_price + 1000; //Tillæg for unlimited
        }
        return totalPrice;
    }
}
