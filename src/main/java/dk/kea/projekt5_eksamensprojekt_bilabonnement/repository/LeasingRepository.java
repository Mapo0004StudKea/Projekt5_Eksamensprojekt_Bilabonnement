package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeasingRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private LeasingModel leasingModel;



    //jeg laver en metode som oprette en LeasingMetode
    public void createLeasingContract(LeasingModel leasing){
        final String INSERT_leasingcontract_SQL = "INSERT INTO leasingcontract (employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_leasingcontract_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(),leasing.isIs_unlimited(),leasing.isIs_limited(), leasing.getCar_id());
    }

    public void deleteLeasing(int id){
        String DELETE_LEASING_SQL = "DELETE FROM leasingcontract WHERE id = ?";
        jdbcTemplate.update(DELETE_LEASING_SQL, id);
    }
    public void updateLeasing(LeasingModel leasing){
        String UPDATE_LEASING_SQL = "UPDATE leasingcontract SET employee_name = ?, monthly_price = ?, customer_name = ?, start_leasing = ?, end_leasing = ?, is_unlimited = ?, is_limited = ?, car_id = ? WHERE id = ?";
        jdbcTemplate.update(UPDATE_LEASING_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(),leasing.isIs_unlimited(),leasing.isIs_limited(), leasing.getCar_id());
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




}
