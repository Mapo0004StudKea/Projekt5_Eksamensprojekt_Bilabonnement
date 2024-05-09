package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;


public class CarModel {
        private int id;
        private int serialNumber;
        private int carNumber;
        private String carModel;
        private String carBrand;
        private String carYear;
        private double monthlyPrice;
        private boolean isLeased;
        private String startDate;
        private String endDate;

    public CarModel(int id, int serialNumber, int carNumber, String carModel, String carBrand, String carYear, double monthlyPrice, boolean isLeased, String startDate, String endDate) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.carYear = carYear;
        this.monthlyPrice = monthlyPrice;
        this.isLeased = isLeased;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public boolean isLeased() {
        return isLeased;
    }

    public void setLeased(boolean leased) {
        isLeased = leased;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
