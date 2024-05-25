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


    // Jeg laver en metode som opretter en leasingkontrakt
    public void createLeasingContract(LeasingModel leasing) {
        //SQL-forespørgsel til at indsætte en ny leasingkontrakt i "leasingContract" tabellen
        final String INSERT_LEASING_CONTRACT_SQL = "INSERT INTO leasingcontract (employee_name, monthly_price, customer_name, start_leasing, end_leasing, is_unlimited, is_limited, car_id) VALUES (?,?,?,?,?,?,?,?)";
        //Udfører SQL-forespørgslen med de nødvendige parametre fra leasing-objektet
        jdbcTemplate.update(INSERT_LEASING_CONTRACT_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(), leasing.getIs_unlimited(), leasing.getIs_limited(), leasing.getCar_id());
    }

    // Metode til at slette en leasingkontrakt baseret på ID
    public void deleteLeasing(int id) {
        //SQL-forespørgsel til at slette en leasingkontrakt fra "leasingContract" tabellen baseret på ID
        String DELETE_LEASING_SQL = "DELETE FROM leasingcontract WHERE id = ?";
        //Udfører SQL-forespørgslen med det givne ID
        jdbcTemplate.update(DELETE_LEASING_SQL, id);
    }

    //Metode til at opdatere en eksisterende leasingkontrakt
    public void updateLeasing(LeasingModel leasing) {
        //SQL-forespørgsel til at opdatere en leasingkontrakt i "leasingContract" tabellen
        String UPDATE_LEASING_SQL = "UPDATE leasingcontract SET employee_name = ?, monthly_price = ?, customer_name = ?, start_leasing = ?, end_leasing = ?, is_unlimited = ?, is_limited = ?, car_id = ? WHERE id = ?";
        //Udfører SQL-forespørgslen med de nødvendige parametre fra leasing-objektet
        jdbcTemplate.update(UPDATE_LEASING_SQL, leasing.getEmployee_name(), leasing.getMonthly_price(), leasing.getCustomer_name(), leasing.getStart_leasing(), leasing.getEnd_leasing(), leasing.getIs_unlimited(), leasing.getIs_limited(), leasing.getCar_id(), leasing.getId());
    }

    //Metode til at finde en leasingkontrakt baseret på ID
    public LeasingModel findById(int id) {
        //SQL-forespørgsel til at finde en leasingkontrakt i "leasingContract" tabellen baseret på ID
        String FIND_LEASING_SQL = "SELECT * FROM leasingcontract WHERE id = ?";
        //Udfører SQL-forespørgslen og mapper resultatet til et LeasingModel-objekt
        return jdbcTemplate.queryForObject(FIND_LEASING_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(LeasingModel.class));
    }

    //Metode til at få en liste af alle leasingkontrakter
    public List<LeasingModel> getListOfLeasingContracts() {
        // SQL-forespørgsel til at hente alle leasingkontrakter fra "leasingContract" tabellen
        String GET_LIST_SQL = "SELECT * FROM leasingcontract";
        // Udfører SQL-forespørgslen og mapper resultaterne til en liste af LeasingModel objekter
        List<LeasingModel> leasingModelList = jdbcTemplate.query(GET_LIST_SQL, new BeanPropertyRowMapper<>(LeasingModel.class));
        // Returnerer listen af leasingkontrakter
        return leasingModelList;
    }

    // Metode til at få en liste af leasingkontrakter baseret på bil-ID
    public List<LeasingModel> getListOfLeasingContractsById(int id) {
        // SQL-forespørgsel til at hente leasingkontrakter fra "leasingContract" tabellen baseret på bil-ID
        String GET_LIST_SQL = "SELECT * FROM leasingcontract WHERE car_id = ?";
        // Udfører SQL-forespørgslen og mapper resultaterne til en liste af LeasingModel objekter
        List<LeasingModel> leasingModelList = jdbcTemplate.query(GET_LIST_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(LeasingModel.class));
        // Returnerer listen af leasingkontrakter
        return leasingModelList;
    }

    // Metode til at få en enkelt leasingkontrakt baseret på bil-ID
    public LeasingModel getLeasingContractsById(int id) {
        // SQL-forespørgsel til at hente en leasingkontrakt fra "leasingContract" tabellen baseret på bil-ID
        String GET_LIST_SQL = "SELECT * FROM leasingcontract WHERE car_id = ?";
        // Udfører SQL-forespørgslen og mapper resultatet til et LeasingModel-objekt
        LeasingModel leasingModel = jdbcTemplate.queryForObject(GET_LIST_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(LeasingModel.class));
        // Returnerer leasingkontrakten
        return leasingModel;
    }

    //Metode til at finde bil-ID med flest leasingaftaler
    public int getCarWithMostLeasingAgreements() {
        //SQL-forespørgsel til at finde bil-ID med flest leasingaftaler, grupperet efter bil-ID og sorteret efter antal leasingaftaler i faldende rækkefølge
        String GET_CAR_WITH_MOST_LEASING_AGREEMENTS_SQL = "SELECT car_id FROM leasingcontract GROUP BY car_id ORDER BY COUNT(*) DESC LIMIT 1";
        //Udfører SQL-forespørgslen og returnerer bil-ID
        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_LEASING_AGREEMENTS_SQL, int.class);
    }

    //Metode til at finde antal leasingaftaler for bil med flest leasingaftaler
    public int getCarWithMostLeasingAgreementsByCount() {
        //SQL-forespørgsel til at finde antal leasingaftaler for bil med flest leasingaftaler, grupperet efter bil-ID og sorteret efter antal leasingaftaler i faldende rækkefølge
        String GET_CAR_WITH_MOST_LEASING_AGREEMENTS_BY_COUNT_SQL = "SELECT COUNT(car_id) AS report_count FROM leasingcontract GROUP BY car_id ORDER BY report_count DESC LIMIT 1";
        //Udfører SQL-forespørgslen og returnerer antal leasingaftaler
        return jdbcTemplate.queryForObject(GET_CAR_WITH_MOST_LEASING_AGREEMENTS_BY_COUNT_SQL, int.class);
    }
}