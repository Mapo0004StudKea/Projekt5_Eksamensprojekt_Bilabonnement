package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.time.LocalDate;

public class LeasingModel {
    int id;
    double leasingPrice;
    LocalDate startLeasing;
    LocalDate endLeasing;
    String customerName;

    boolean threeMonths;
    boolean sixMonths;
    boolean twelveMonths;

    boolean isUnlimited;

    public LeasingModel(double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean threeMonths, boolean sixMonths, boolean twelveMonths, boolean isUnlimited) {
        this.leasingPrice = leasingPrice;
        this.startLeasing = startLeasing;
        this.endLeasing = endLeasing;
        this.customerName = customerName;
        this.threeMonths = threeMonths;
        this.sixMonths = sixMonths;
        this.twelveMonths = twelveMonths;
        this.isUnlimited = isUnlimited;
    }

    public LeasingModel(int id, double leasingPrice, LocalDate startLeasing, LocalDate endLeasing, String customerName, boolean threeMonths, boolean sixMonths, boolean twelveMonths, boolean isUnlimited) {
        this.id = id;
        this.leasingPrice = leasingPrice;
        this.startLeasing = startLeasing;
        this.endLeasing = endLeasing;
        this.customerName = customerName;
        this.threeMonths = threeMonths;
        this.sixMonths = sixMonths;
        this.twelveMonths = twelveMonths;
        this.isUnlimited = isUnlimited;
    }

    public LeasingModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLeasingPrice() {
        return leasingPrice;
    }

    public void setLeasingPrice(double leasingPrice) {
        this.leasingPrice = leasingPrice;
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

    public boolean isThreeMonths() {
        return threeMonths;
    }

    public void setThreeMonths(boolean threeMonths) {
        this.threeMonths = threeMonths;
    }

    public boolean isSixMonths() {
        return sixMonths;
    }

    public void setSixMonths(boolean sixMonths) {
        this.sixMonths = sixMonths;
    }

    public boolean isTwelveMonths() {
        return twelveMonths;
    }

    public void setTwelveMonths(boolean twelveMonths) {
        this.twelveMonths = twelveMonths;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

}
