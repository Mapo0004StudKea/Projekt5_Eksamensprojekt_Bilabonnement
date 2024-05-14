package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;



public class DamageReportModel {
    int id;
    String report_name;
    String report_description;
    int car_id;


    public DamageReportModel(int id, String report_name, String report_description) {
        this.id = id;
        this.report_name = report_name;
        this.report_description = report_description;
    }

    public DamageReportModel() {
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
}
