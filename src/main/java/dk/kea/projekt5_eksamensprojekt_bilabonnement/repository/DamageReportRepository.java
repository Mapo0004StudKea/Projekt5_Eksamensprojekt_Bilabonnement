package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DamageReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CarModel carModel;


    //lave et nyt damageReportModel
    public void createNewDamageReport(DamageReportModel damageReportModel){
        //indsæt sql statement
        final String CREA_NEW_DamageReportModel_SQL = "INSERT INTO damagereport (report_name, report_description, report_employee_name, car_id) values (?,?,?,?)";
        jdbcTemplate.update(CREA_NEW_DamageReportModel_SQL, damageReportModel.getReport_name(), damageReportModel.getReport_description(), damageReportModel.getCar_id());
    }

    public void deletedDamageReportById(int id){
        final String DELETED_BY_ID = "DELETED FROM damagereport where id =? ";
        jdbcTemplate.update(DELETED_BY_ID, id);
    }

    public void updateDamageReportInDatabase(DamageReportModel damageReportModel){
        String UPDATE_BY_DAMAGE_REPORT = "UPDATE damagereport SET id = ?, report_name = ?, report_description = ?,report_employee_name = ?";
        jdbcTemplate.update(UPDATE_BY_DAMAGE_REPORT,damageReportModel.getId(),damageReportModel.getReport_name(), damageReportModel.getReport_description(), damageReportModel.getReport_employee_name());
    }

    public List<DamageReportModel> getFullListOfReport(){
        final String DISPLAY_FULL_LIST_OF_damage_report_SQL = "SELECT * FROM damagereport";
        List<DamageReportModel> damageReportModels = jdbcTemplate.query(DISPLAY_FULL_LIST_OF_damage_report_SQL, new BeanPropertyRowMapper<>(DamageReportModel.class));
        return damageReportModels;
    }

}
