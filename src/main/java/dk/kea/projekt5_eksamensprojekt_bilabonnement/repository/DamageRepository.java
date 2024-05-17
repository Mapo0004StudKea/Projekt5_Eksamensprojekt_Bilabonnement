package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.DamageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * leasing service class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */
@Repository
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
    public List<DamageModel> findAllDamage(){
        String GET_ALL_DAMAGE_SQL = "SELECT * FROM damages";
        List<DamageModel> damageList = jdbcTemplate.query(GET_ALL_DAMAGE_SQL, new BeanPropertyRowMapper<>(DamageModel.class));
        return damageList;
    }

    public List<DamageModel> findDamageReportById(int reportID){
        String GET_DAMAGE_SQL = "SELECT * FROM damages WHERE damagereport_id = ?";
        List<DamageModel> damageModelList = jdbcTemplate.query(GET_DAMAGE_SQL, new Object[]{reportID}, new BeanPropertyRowMapper<>(DamageModel.class));
        return damageModelList;
    }
    public DamageModel findDamageById(int id){
        //Find sql
        String GET_DAMAGE_SQL = "SELECT * FROM damages WHERE id = ?";
        //return query
        return jdbcTemplate.queryForObject(GET_DAMAGE_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(DamageModel.class));
    }
    public DamageModel updateDamageById(int id){
        String GET_UPDATE_DAMAGE_BY_ID = "SELECT * FROM damages WHERE id = ?";
        return jdbcTemplate.queryForObject(GET_UPDATE_DAMAGE_BY_ID, new Object[]{id}, DamageModel.class);
    }

    public void updateDamageDatabase(DamageModel damage){
        String UPDATE_DAMAGE_SQL = "UPDATE damages SET damage_name = ?, damage_price = ?, damage_description = ?, damageReport_id = ? WHERE id = ?";
        jdbcTemplate.update(UPDATE_DAMAGE_SQL, damage.getDamage_name(), damage.getDamage_price(), damage.getDamage_description(), damage.getDamageReport_id(), damage.getId());
    }


    public List<DamageModel> getFullPriceFromId(int damageReport_id) {
        String GET_FULL_PRICE_FROM_ID_SQL = "SELECT * FROM damages WHERE damageReport_id = ?";
        List<DamageModel> damageModelList = jdbcTemplate.query(GET_FULL_PRICE_FROM_ID_SQL, new Object[]{damageReport_id}, new BeanPropertyRowMapper<>(DamageModel.class));
        return damageModelList;
    }
}

