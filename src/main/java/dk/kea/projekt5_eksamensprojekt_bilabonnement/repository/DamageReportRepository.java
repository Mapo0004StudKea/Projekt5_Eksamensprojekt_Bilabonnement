package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageReportModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * damagereport repository class
 *
 * @author Sebastian Drumm, sedr
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
@Repository
public class DamageReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CarModel carModel;


    //lave et nyt damageReportModel
    public void createNewDamageReport(DamageReportModel damageReportModel){
        //indsæt sql statement
        final String CREA_NEW_DamageReportModel_SQL = "INSERT INTO damagereport (report_name, report_description, report_employee_name, car_id) values (?,?,?,?)";
        jdbcTemplate.update(CREA_NEW_DamageReportModel_SQL, damageReportModel.getReport_name(), damageReportModel.getReport_description(), damageReportModel.getReport_employee_name(), damageReportModel.getCar_id());
    }

    public void deletedDamageReportById(int id){
        final String DELETED_BY_ID = "DELETE FROM damagereport where id =? ";
        jdbcTemplate.update(DELETED_BY_ID, id);
    }

    public DamageReportModel getDamageReportInDatabaseByID(int id) {
        String GET_REPORT_BY_ID = "SELECT * FROM damagereport WHERE id = ?";
        return jdbcTemplate.queryForObject(GET_REPORT_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(DamageReportModel.class));
    }

    public void UpdateDamageReportEntryInDatabase(DamageReportModel damageReportModel) {
        //update sql
        String UPDATE_REPORT_BY_ID = "UPDATE damagereport SET report_name =?, report_description = ?, report_employee_name =?, car_id =?   WHERE id = ?";
        //update db vha. JdbcTemplate
        jdbcTemplate.update(UPDATE_REPORT_BY_ID, damageReportModel.getReport_name(),damageReportModel.getReport_description(), damageReportModel.getReport_employee_name(), damageReportModel.getCar_id(), damageReportModel.getId());
    }

    public List<DamageReportModel> getFullListOfReport(){
        final String DISPLAY_FULL_LIST_OF_damage_report_SQL = "SELECT * FROM damagereport";
        List<DamageReportModel> damageReportModels = jdbcTemplate.query(DISPLAY_FULL_LIST_OF_damage_report_SQL, new BeanPropertyRowMapper<>(DamageReportModel.class));
        return damageReportModels;
    }

    public List<DamageReportModel> getAllReportsByCarId(int id) {
        final String GET_ALL_REPORTS_BY_CAR_ID_SQL = "select * from damagereport where car_id = ?;";
        List<DamageReportModel> damageReportModels = jdbcTemplate.query(GET_ALL_REPORTS_BY_CAR_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(DamageReportModel.class));
        return damageReportModels;
    }

    public Integer getCarWithMostDamageReports() {
        String GET_CAR_WITH_MOST_DAMAGE_REPORTS_SQL =
                "SELECT car_id FROM damagereport GROUP BY car_id ORDER BY COUNT(*) DESC LIMIT 1";
        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_DAMAGE_REPORTS_SQL, Integer.class);
    }

    public int getCarWithMostDamageReportsByCount() {
        String GET_CAR_WITH_MOST_DAMAGE_REPORTS_BY_COUNT_SQL = "SELECT COUNT(car_id) AS report_count FROM damagereport GROUP BY car_id ORDER BY report_count DESC LIMIT 1";        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_DAMAGE_REPORTS_BY_COUNT_SQL, int.class);
    }
}
