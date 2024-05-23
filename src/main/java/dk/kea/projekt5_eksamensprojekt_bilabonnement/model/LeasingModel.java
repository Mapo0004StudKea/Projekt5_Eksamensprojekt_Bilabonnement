package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.time.LocalDate;

/**
 * leasing model class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */

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

    public LeasingModel(int id, String employee_name, double monthly_price, String customer_name, LocalDate start_leasing, LocalDate end_leasing, boolean is_unlimited, boolean is_limited, int car_id) {
        this.id = id;
        this.employee_name = employee_name;
        this.monthly_price = monthly_price;
        this.customer_name = customer_name;
        this.start_leasing = start_leasing;
        this.end_leasing = end_leasing;
        this.is_unlimited = is_unlimited;
        this.is_limited = is_limited;
        this.car_id = car_id;
    }

    public LeasingModel(String employee_name, double monthly_price, String customer_name, LocalDate start_leasing, LocalDate end_leasing, boolean is_unlimited, boolean is_limited, int car_id) {
        this.employee_name = employee_name;
        this.monthly_price = monthly_price;
        this.customer_name = customer_name;
        this.start_leasing = start_leasing;
        this.end_leasing = end_leasing;
        this.is_unlimited = is_unlimited;
        this.is_limited = is_limited;
        this.car_id = car_id;
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
    //Metode til at lægge tillæg til den månedelige pris. getMetoden bliver kaldt i htmlen 'watchLeasingAgreements'.
    public double getMonthly_price_with_addon(){
        double finalPrice = 0;
        if(is_unlimited){
            finalPrice = 1000;
        }
        else if(is_limited){
            finalPrice = 500;
        }
        return monthly_price + finalPrice;
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

    public boolean getIs_limited() {
        return is_limited;
    }

    public void setIs_limited(boolean is_limited) {
        this.is_limited = is_limited;
    }

    public boolean getIs_unlimited() {
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
