package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;

/**
 * carReport model class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 *
 * Vores bilRapport model med Constructors, getters og setters.
 */

public class CarReportModel {
    private CarModel carModel;
    private DamageReportModel damageReportModel;

    public CarReportModel(CarModel carModel, DamageReportModel damageReportModel) {
        this.carModel = carModel;
        this.damageReportModel = damageReportModel;
    }

    public CarReportModel() {
    }

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
