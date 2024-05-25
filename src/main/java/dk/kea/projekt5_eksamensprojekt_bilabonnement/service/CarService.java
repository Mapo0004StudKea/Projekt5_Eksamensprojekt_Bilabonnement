package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * car service class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 *
 * Forskelliges metoder der bliver brugt til at udrang pris på forskellige måder, for biler, leasede biler og ikke-leasede biler.
 */

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

        //En metode der beregner den samlede månedlige pris for alle biler
        public double calculateTotalMonthlyPrice() {
            //Henter en liste af alle biler fra databasen
            List<CarModel> cars = carRepository.getFullListOfCars();
            //Initialiserer den samlede pris
            double totalPrice = 0;
            //Går igennem hver bil i listen
            for (CarModel car : cars) {
                    //Tilføjer bilens månedlige pris til den samlede pris
                    totalPrice += car.getMonthly_price();
            }
            //Returnerer den samlede månedlige pris for alle biler
            return totalPrice;
        }

        //En metode der beregner den samlede månedlige pris for leasede biler
        public double calculateTotalMonthlyPriceForLeasedCars() {
            //Henter en liste af leasede biler fra databasen
            List<CarModel> leasedCars = carRepository.getLeasedCars();
            //Initialiserer den samlede pris for leasede biler
            double totalLeasedMonthlyPrice = 0;
            //Går igennem hver leasede bil i listen
            for (CarModel car : leasedCars) {
                //Tilføjer bilens månedlige pris til den samlede pris for leasede biler
                totalLeasedMonthlyPrice += car.getMonthly_price();
            }
            //Returnerer den samlede månedlige pris for alle leasede biler
            return totalLeasedMonthlyPrice;
        }

        //Beregner den samlede månedlige pris for ikke-leasede biler
        public double calculateTotalMonthlyPriceForNonLeasedCars() {
            //Henter en liste af ikke-leasede biler fra databasen
            List<CarModel> nonLeasedCars = carRepository.getNonLeasedCars();
            //Initialiserer den samlede pris for ikke-leasede biler
            double totalNonLeasedMonthlyPrice = 0;
            //Går igennem hver ikke-leasede bil i listen
            for (CarModel car : nonLeasedCars) {
                //Tilføjer bilens månedlige pris til den samlede pris for ikke-leasede biler
                totalNonLeasedMonthlyPrice += car.getMonthly_price();
            }
            //Returnerer den samlede månedlige pris for ikke-leasede biler
            return totalNonLeasedMonthlyPrice;
        }
    }
