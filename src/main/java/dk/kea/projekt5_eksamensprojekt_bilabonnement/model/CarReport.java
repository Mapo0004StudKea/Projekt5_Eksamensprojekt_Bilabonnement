package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

public class CarReport {
    private CarModel carModel;
    private DamageReportModel damageReportModel;

    public CarReport(CarModel carModel, DamageReportModel damageReportModel) {
        this.carModel = carModel;
        this.damageReportModel = damageReportModel;
    }

    // Getters and Setters
    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public DamageReportModel getDamageReportModel() {
        return damageReportModel;
    }

    public void setDamageReportModel(DamageReportModel damageReportModel) {
        this.damageReportModel = damageReportModel;
    }
}
