package dk.kea.projekt5_eksamensprojekt_bilabonnement.model;

/**
 * Skade model class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 *
 * Vores skade model med Constructors, getters og setters.
 */
public class DamageModel {
    int id;
    String damage_name;
    double damage_price;
    String damage_description;
    int damageReport_id;

    public DamageModel(int id, String damage_name, double damage_price, String damage_description, int damageReport_id) {
        this.id = id;
        this.damage_name = damage_name;
        this.damage_price = damage_price;
        this.damage_description = damage_description;
        this.damageReport_id = damageReport_id;
    }

    public DamageModel(String damage_name, double damage_price, String damage_description, int damageReport_id) {
        this.damage_name = damage_name;
        this.damage_price = damage_price;
        this.damage_description = damage_description;
        this.damageReport_id = damageReport_id;
    }

    public DamageModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamage_name() {
        return damage_name;
    }

    public void setDamage_name(String damage_name) {
        this.damage_name = damage_name;
    }

    public double getDamage_price() {
        return damage_price;
    }

    public void setDamage_price(double damage_price) {
        this.damage_price = damage_price;
    }

    public String getDamage_description() {
        return damage_description;
    }

    public void setDamage_description(String damage_description) {
        this.damage_description = damage_description;
    }

    public int getDamageReport_id() {
        return damageReport_id;
    }

    public void setDamageReport_id(int damageReport_id) {
        this.damageReport_id = damageReport_id;
    }
}
