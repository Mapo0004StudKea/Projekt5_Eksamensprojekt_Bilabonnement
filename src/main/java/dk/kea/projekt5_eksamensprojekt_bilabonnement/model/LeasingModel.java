package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.time.LocalDate;

public class LeasingModel {
    int id;
    String employeeName;
    double monthlyPrice;
    String customerName;
    LocalDate startLeasing;
    LocalDate endLeasing;
    boolean isLimited;
    boolean isUnlimited;
    int carId;

    public LeasingModel(int id, double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean isLimited, boolean isUnlimited, int carId, String employeeName) {
        this.id = id;
        this.monthlyPrice = leasingPrice;
        this.startLeasing = startLeasing;
        this.endLeasing = endLeasing;
        this.customerName = customerName;
        this.isLimited = isLimited;
        this.isUnlimited = isUnlimited;
        this.carId = carId;
        this.employeeName = employeeName;
    }

    public LeasingModel(double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean isLimited, boolean isUnlimited, int carId) {
        this.monthlyPrice = leasingPrice;
        this.startLeasing = startLeasing;
        this.endLeasing = endLeasing;
        this.customerName = customerName;
        this.isLimited = isLimited;
        this.isUnlimited = isUnlimited;
        this.carId = carId;
    }

    public LeasingModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public LocalDate getStartLeasing() {
        return startLeasing;
    }

    public void setStartLeasing(LocalDate startLeasing) {
        this.startLeasing = startLeasing;
    }

    public LocalDate getEndLeasing() {
        return endLeasing;
    }

    public void setEndLeasing(LocalDate endLeasing) {
        this.endLeasing = endLeasing;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
