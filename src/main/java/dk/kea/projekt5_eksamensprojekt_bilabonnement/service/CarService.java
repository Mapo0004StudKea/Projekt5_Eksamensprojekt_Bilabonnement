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
 */

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public double calculateTotalMonthlyPrice() {
        List<CarModel> cars = carRepository.getFullListOfCars();
        double totalPrice = 0;
        for (CarModel car : cars) {
            totalPrice += car.getMonthly_price();
        }
        return totalPrice;
    }

    public double calculateTotalMonthlyPriceForLeasedCars() {
        List<CarModel> leasedCars = carRepository.getLeasedCars();
        double totalLeasedMonthlyPrice = 0;
        for (CarModel car : leasedCars) {
            totalLeasedMonthlyPrice += car.getMonthly_price();
        }
        return totalLeasedMonthlyPrice;
    }

    public double calculateTotalMonthlyPriceForNonLeasedCars() {
        List<CarModel> nonLeasedCars = carRepository.getNonLeasedCars();
        double totalNonLeasedMonthlyPrice = 0;
        for (CarModel car : nonLeasedCars) {
            totalNonLeasedMonthlyPrice += car.getMonthly_price();
        }
        return totalNonLeasedMonthlyPrice;
    }
}
