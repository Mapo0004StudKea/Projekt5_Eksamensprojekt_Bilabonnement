package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;
import java.util.List;

public class CarsAndLeasingModels{
        private List<CarModel> cars;
        private List<LeasingModel> leasingModels;

        public CarsAndLeasingModels(List<CarModel> cars, List<LeasingModel> leasingModels) {
            this.cars = cars;
            this.leasingModels = leasingModels;
        }

        public List<CarModel> getCars() {
            return cars;
        }

        public void setCars(List<CarModel> cars) {
            this.cars = cars;
        }

        public List<LeasingModel> getLeasingModels() {
            return leasingModels;
        }

        public void setLeasingModels(List<LeasingModel> leasingModels) {
            this.leasingModels = leasingModels;
        }
    }

