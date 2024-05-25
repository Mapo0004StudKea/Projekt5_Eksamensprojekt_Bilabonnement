package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

import java.time.LocalDate;

/**
 * Damage Report model class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 * @author Viggo Beck, vibe0002@stud.kea.dk
 *
 * Vores skaderapport model med Constructors, getters og setters.
 */

public class DamageReportModel {
    int id;
    String report_name;
    String report_description;
    String report_employee_name;
    LocalDate report_Damage_Date;
    int car_id;

    public DamageReportModel() {
    }

    public DamageReportModel(int id, String report_name, String report_description, String report_employee_name, int car_id, LocalDate report_Damage_Date) {
        this.id = id;
        this.report_name = report_name;
        this.report_description = report_description;
        this.report_employee_name = report_employee_name;
        this.car_id = car_id;
        this.report_Damage_Date = report_Damage_Date;
    }

    public DamageReportModel(String report_name, String report_description, String report_employee_name, int car_id, LocalDate report_Damage_Date) {
        this.report_name = report_name;
        this.report_description = report_description;
        this.report_employee_name = report_employee_name;
        this.car_id =car_id;
        this.report_Damage_Date = report_Damage_Date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getReport_description() {
        return report_description;
    }

    public void setReport_description(String report_description) {
        this.report_description = report_description;
    }

    public String getReport_employee_name() {
        return report_employee_name;
    }

    public void setReport_employee_name(String report_employee_name) {
        this.report_employee_name = report_employee_name;
    }

    public LocalDate getReport_Damage_Date() {
        return report_Damage_Date;
    }

    public void setReport_Damage_Date(LocalDate report_Damage_Date) {
        this.report_Damage_Date = report_Damage_Date;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
}
