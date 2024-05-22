package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;

/**
 * leasing repository class
 *
 * @author Viggo Beck, vibe0002@stud.kea.dk
 * @author Sebastian Drumm, sedr0001@stud.kea.dk
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 */

@Repository
public class LeasingRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    //jeg laver en metode som oprette en LeasingMetode
    public void createLeasingContract(LeasingModel leasing){
        final String INSERT_leasingcontract_SQL = "INSERT INTO leasingcontract (employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_leasingcontract_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(),leasing.getIs_unlimited(),leasing.getIs_limited(), leasing.getCar_id());
    }

    public void deleteLeasing(int id){
        String DELETE_LEASING_SQL = "DELETE FROM leasingcontract WHERE id = ?";
        jdbcTemplate.update(DELETE_LEASING_SQL, id);
    }
    public void updateLeasing(LeasingModel leasing){
        String UPDATE_LEASING_SQL = "UPDATE leasingcontract SET employee_name = ?, monthly_price = ?, customer_name = ?, start_leasing = ?, end_leasing = ?, is_unlimited = ?, is_limited = ?, car_id = ? WHERE id = ?";
        jdbcTemplate.update(UPDATE_LEASING_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(), leasing.getIs_unlimited(), leasing.getIs_limited(), leasing.getCar_id(), leasing.getId());
    }

    public LeasingModel findById(int id){
        String FIND_LEASING_SQL ="SELECT * FROM leasingcontract WHERE id = ?";
        return jdbcTemplate.queryForObject(FIND_LEASING_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(LeasingModel.class));

    }
    public List<LeasingModel> getListOfLeasingContracts(){
        String GET_LIST_SQL ="SELECT * FROM leasingcontract";
        List<LeasingModel> leasingModelList = jdbcTemplate.query(GET_LIST_SQL, new BeanPropertyRowMapper<>(LeasingModel.class));
        return leasingModelList;
    }
    public List<LeasingModel> getListOfLeasingContractsById(int id){
        String GET_LIST_SQL ="SELECT * FROM leasingcontract WHERE car_id = ?";
        List<LeasingModel> leasingModelList = jdbcTemplate.query(GET_LIST_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(LeasingModel.class));
        return leasingModelList;
    }

    public int getCarWithMostLeasingAgreements() {
        String GET_CAR_WITH_MOST_DAMAGE_REPORTS_SQL =
                "SELECT car_id FROM leasingcontract GROUP BY car_id ORDER BY COUNT(*) DESC LIMIT 1";
        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_DAMAGE_REPORTS_SQL, int.class);
    }

    public int getCarWithMostLeasingAgreementsByCount() {
        String GET_CAR_WITH_MOST_DAMAGE_REPORTS_BY_COUNT_SQL =
                "SELECT COUNT(car_id) AS report_count FROM leasingcontract GROUP BY car_id ORDER BY report_count DESC LIMIT 1";
        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_DAMAGE_REPORTS_BY_COUNT_SQL, int.class);
    }
}