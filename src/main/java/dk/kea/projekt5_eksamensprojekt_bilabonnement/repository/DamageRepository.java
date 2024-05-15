package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * leasing service class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 */

public class DamageRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private DamageModel damageModel;

    public void createNewDamage(DamageModel damage){
        final String INSERT_DAMAGE_SQL = "INSERT INTO damages (damage_name, damage_price, damage_description, damageReport_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_DAMAGE_SQL, damage.getDamage_name(), damage.getDamage_price(), damage.getDamage_description(), damage.getDamageReport_id());
    }
    public void deleteDamage(int id){
        String DELETE_DAMAGE_SQL = "DELETE FROM damages WHERE id = ?";
        jdbcTemplate.update(DELETE_DAMAGE_SQL, id);
    }
    public DamageModel findDamageById(int id){
        String GET_DAMAGE_SQL = "SELECT * FROM damages WHERE id = ?";
        return jdbcTemplate.queryForObject(GET_DAMAGE_SQL, new BeanPropertyRowMapper<>(DamageModel.class), id);
    }
    public List<DamageModel> findDamageReportById(int reportID){
        String GET_DAMAGE_SQL = "SELECT * FROM damages WHERE damagereport_id = ?";
        List<DamageModel> damageModelList = jdbcTemplate.query(GET_DAMAGE_SQL, new Object[]{reportID}, new BeanPropertyRowMapper<>(DamageModel.class));
        return damageModelList;
    }


    public List<DamageModel> findAllDamage(){
        String GET_ALL_DAMAGE_SQL = "SELECT * FROM damages";
        List<DamageModel> damageList = jdbcTemplate.query(GET_ALL_DAMAGE_SQL, new BeanPropertyRowMapper<>(DamageModel.class));
        return damageList;
    }


    
}

