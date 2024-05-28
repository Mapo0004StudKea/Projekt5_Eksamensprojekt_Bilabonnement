package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.util.PrimitiveIterator;

/**
 * car model class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 *
 * Vores bil model med Constructors, getters og setters.
 */
public class CarModel {
        private int id;
        private String car_picture;
        private String car_full_name;
        private String car_Serialnr;
        private String car_number;
        private String car_model;
        private String car_name;
        private String car_year;
        private double monthly_price;
        private boolean is_leased;
        private String car_description;

    public CarModel(String car_picture, String car_full_name, String  car_Serialnr, String  car_number, String car_model, String car_name, String car_year, double monthly_price, boolean is_leased, String car_description) {
        this.car_picture = car_picture;
        this.car_full_name = car_full_name;
        this.car_Serialnr = car_Serialnr;
        this.car_number = car_number;
        this.car_model = car_model;
        this.car_name = car_name;
        this.car_year = car_year;
        this.monthly_price = monthly_price;
        this.is_leased = is_leased;
        this.car_description = car_description;
    }

    public CarModel(int id, String car_name, boolean is_leased) {
        this.id = id;
        this.car_name = car_name;
        this.is_leased = is_leased;
    }

    public String getCar_description() {
        return car_description;
    }

    public void setCar_description(String car_description) {
        this.car_description = car_description;
    }

    public CarModel(int id, String  serialNumber, String  carNumber, String carModel, String carBrand, String carYear, double monthlyPrice, boolean isLeased) {
        this.id = id;
        this.car_Serialnr = serialNumber;
        this.car_number = carNumber;
        this.car_model = carModel;
        this.car_name = carBrand;
        this.car_year = carYear;
        this.monthly_price = monthlyPrice;
        this.is_leased = isLeased;

    }

    public CarModel(int id, String car_picture, String car_full_name, String  car_Serialnr, String  car_number, String car_model, String car_name, String car_year, double monthly_price, boolean is_leased, String car_description) {
        this.id = id;
        this.car_picture = car_picture;
        this.car_full_name = car_full_name;
        this.car_Serialnr = car_Serialnr;
        this.car_number = car_number;
        this.car_model = car_model;
        this.car_name = car_name;
        this.car_year = car_year;
        this.monthly_price = monthly_price;
        this.is_leased = is_leased;
        this.car_description = car_description;
    }

    public CarModel(String  car_Serialnr, String  car_number, String car_model, String car_name, String car_year, double monthly_price, boolean is_leased) {
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

    public String  getCar_Serialnr() {
        return car_Serialnr;
    }

    public void setCar_Serialnr(String  car_Serialnr) {
        this.car_Serialnr = car_Serialnr;
    }

    public String  getCar_number() {
        return car_number;
    }

    public void setCar_number(String  car_number) {
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

    public String getCar_picture() {
        return car_picture;
    }

    public void setCar_picture(String car_picture) {
        this.car_picture = car_picture;
    }

    public String getCar_full_name() {
        return car_full_name;
    }

    public void setCar_full_name(String car_full_name) {
        this.car_full_name = car_full_name;
    }
}
