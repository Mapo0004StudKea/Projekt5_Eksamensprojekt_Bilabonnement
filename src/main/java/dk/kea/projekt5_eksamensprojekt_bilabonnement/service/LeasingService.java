package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import java.util.List;

/**
 * leasing service class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 *  @author Viktor Rasmussen, vira0004@stud.kea.dk
 */

    @Service
    public class LeasingService {

    //Metode der sørger for at biler der er limited kun kan lejes i en periode på 5 måneder, og unlimited kun kan lejes fra 3 måneder og op.
    public String checkLeasingDate(LocalDate startLeasing, LocalDate endLeasing, boolean isUnlimited, boolean isLimited) {

        if (isLimited && ChronoUnit.DAYS.between(startLeasing, endLeasing) > 150) {
            return "For en begrænset leasingaftale, kan du kun leje 150 dage";
            //Det blev nødt til at være chronounit her, der er mere nøjagtig end ovenover. Ellers ville det ikke virke af en eller anden grund.
        } else if (isUnlimited && ChronoUnit.DAYS.between(startLeasing, endLeasing) < 90) {
            return "For en ubegrænset lejeaftale, skal længden være minimum 3 måneder.";
        } else if (isUnlimited && isLimited) {
            return "En leasingaftale kan ikke være både begrænset og ubegrænset.";
        }
        return null;
    }
}
