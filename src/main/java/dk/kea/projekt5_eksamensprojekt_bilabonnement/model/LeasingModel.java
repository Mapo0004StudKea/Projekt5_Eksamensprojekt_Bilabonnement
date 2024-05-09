package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.time.LocalDate;

public class LeasingModel {
    int id;
    String employee_name;
    double monthly_price;
    String customer_name;
    LocalDate start_leasing;
    LocalDate end_leasing;
    boolean is_unlimited;
    boolean is_limited;
    int car_id;

    public LeasingModel(int id, double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean isLimited, boolean isUnlimited, int carId, String employeeName) {
        this.id = id;
        this.monthly_price = leasingPrice;
        this.start_leasing = startLeasing;
        this.end_leasing = endLeasing;
        this.customer_name = customerName;
        this.is_limited = isLimited;
        this.is_unlimited = isUnlimited;
        this.car_id = carId;
        this.employee_name = employeeName;
    }

    public LeasingModel(double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean isLimited, boolean isUnlimited, int carId, String employee_name) {
        this.monthly_price = leasingPrice;
        this.start_leasing = startLeasing;
        this.end_leasing = endLeasing;
        this.customer_name = customerName;
        this.is_limited = isLimited;
        this.is_unlimited = isUnlimited;
        this.employee_name = employee_name;
        this.car_id = carId;
    }

    public LeasingModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(double monthly_price) {
        this.monthly_price = monthly_price;
    }

    public LocalDate getStart_leasing() {
        return start_leasing;
    }

    public void setStart_leasing(LocalDate start_leasing) {
        this.start_leasing = start_leasing;
    }

    public LocalDate getEnd_leasing() {
        return end_leasing;
    }

    public void setEnd_leasing(LocalDate end_leasing) {
        this.end_leasing = end_leasing;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public boolean isIs_limited() {
        return is_limited;
    }

    public void setIs_limited(boolean is_limited) {
        this.is_limited = is_limited;
    }

    public boolean isIs_unlimited() {
        return is_unlimited;
    }

    public void setIs_unlimited(boolean is_unlimited) {
        this.is_unlimited = is_unlimited;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
}
