package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

/**
 * car model class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
public class CarModel {
        private int id;
        private int car_Serialnr;
        private int car_number;
        private String car_model;
        private String car_name;
        private String car_year;
        private double monthly_price;
        private boolean is_leased;


    public CarModel(int id, int serialNumber, int carNumber, String carModel, String carBrand, String carYear, double monthlyPrice, boolean isLeased) {
        this.id = id;
        this.car_Serialnr = serialNumber;
        this.car_number = carNumber;
        this.car_model = carModel;
        this.car_name = carBrand;
        this.car_year = carYear;
        this.monthly_price = monthlyPrice;
        this.is_leased = isLeased;

    }

    public CarModel(int car_Serialnr, int car_number, String car_model, String car_name, String car_year, double monthly_price, boolean is_leased) {
        this.car_Serialnr = car_Serialnr;
        this.car_number = car_number;
        this.car_model = car_model;
        this.car_name = car_name;
        this.car_year = car_year;
        this.monthly_price = monthly_price;
        this.is_leased = is_leased;
    }

    public CarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_Serialnr() {
        return car_Serialnr;
    }

    public void setCar_Serialnr(int car_Serialnr) {
        this.car_Serialnr = car_Serialnr;
    }

    public int getCar_number() {
        return car_number;
    }

    public void setCar_number(int car_number) {
        this.car_number = car_number;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_year() {
        return car_year;
    }

    public void setCar_year(String car_year) {
        this.car_year = car_year;
    }

    public double getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(double monthly_price) {
        this.monthly_price = monthly_price;
    }

    public boolean isIs_leased() {
        return is_leased;
    }

    public void setIs_leased(boolean is_leased) {
        this.is_leased = is_leased;
    }
}
