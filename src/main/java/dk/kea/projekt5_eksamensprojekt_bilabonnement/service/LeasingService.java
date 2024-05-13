package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeasingService {

    @Autowired
    private LeasingRepository leasingRepository;

    public double calculateLeasingPrice (boolean is_limited, boolean is_unlimited, double monthly_price){
        double totalPrice = 0;
        totalPrice += monthly_price;

        

        return totalPrice;
    }

}
