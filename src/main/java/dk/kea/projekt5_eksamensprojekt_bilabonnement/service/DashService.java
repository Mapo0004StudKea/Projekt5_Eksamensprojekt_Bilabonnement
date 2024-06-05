package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarsAndLeasingModels;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    LeasingRepository leasingRepository;

    // chat Gbdt, har hjulpet en del med denne metode.
    // jeg har lavet en model CarsAndLeasingModels som består af to lister som jeg gerne ville retunere.
    public CarsAndLeasingModels listOfCarsNearEndLeasing(int daysThreshold) {
        //jeg laver en række liste for at kunne håndter metoden
        //jeg skal havde listOfCarsSorted tilbage og udprintes.
        List<CarModel> listOfCarsSorted = new ArrayList<>();
        List<LeasingModel> listOfLeasingSorted = new ArrayList<>();
        //jeg skal undersøge hver bil leasingsList, men skal første hente bilerne
        List<CarModel> carList = carRepository.getFullListOfCars();
        LocalDate today = LocalDate.now();

        // jeg går igennem hver carModel, for at få fat i en enkel bil
        for (CarModel carModel : carList) {
            LeasingModel latestLeasingModel = null;
            // jeg ville kun undersøge de bilser som er leased.
            if (carModel.isIs_leased()) {
                List<LeasingModel> listOfleasing = leasingRepository.getListOfLeasingContractsById(carModel.getId());
                //hvis listen er alt andet en tom, ville jeg gerne havde den sidste lavet LeasingModel
                if (!listOfleasing.isEmpty()) {
                    // her vægler jeg så en enkel ud af hele min liste af leasingaftaler.
                    latestLeasingModel = listOfleasing.get(listOfleasing.size()-1);
                   // System.out.println(latestLeasingModel.getEnd_leasing());
                    //System.out.println(latestLeasingModel.getCar_id());
                }
                //så henter jeg end_leasing, for at tjekke om den er inden for 30 dage.
                LocalDate endLeasingDate = latestLeasingModel.getEnd_leasing();
                if (endLeasingDate != null && ChronoUnit.DAYS.between(today, endLeasingDate) <= daysThreshold) {
                    // og så add'er jeg dem til litsten.
                    listOfLeasingSorted.add(listOfleasing.get(listOfleasing.size()-1));
                    listOfCarsSorted.add(carModel);
                }
            }
        }
        return new CarsAndLeasingModels(listOfCarsSorted, listOfLeasingSorted);
    }
    }
