package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LeasingRepository {
    JdbcTemplate jdbcTemplate;


//jeg laver en metode som oprette en LeasingMetode
    public void leasingNewContrakt(LeasingModel leasingModel){
        final String INSERT_leasingcontract_SQL = "INSERT INTO leasingcontract (leasingPrice, startLeasing, endLeasing, customerName, isLimited, isUnlimited) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_leasingcontract_SQL, leasingModel.getMonthlyPrice(),leasingModel.getStartLeasing(),leasingModel.getEndLeasing(),leasingModel.getCustomerName(),leasingModel.isLimited(),leasingModel.isUnlimited());

    }

}
