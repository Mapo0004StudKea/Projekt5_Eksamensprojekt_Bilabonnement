package dk.kea.projekt5_eksamensprojekt_bilabonnement.repository;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createNewNewCarEntry(CarModel carModel) {
        //insert sql
        final String CREATE_NEW_CAR_ENTRY_SQL = "INSERT INTO car (serialNumber, carNumber, carModel, carBrand, carYear, monthlyPrice, isLeased) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(CREATE_NEW_CAR_ENTRY_SQL, carModel.getSerialNumber(), carModel.getCarNumber(), carModel.getCarModel(), carModel.getCarBrand(), carModel.getCarYear(), carModel.getMonthlyPrice(), carModel.isLeased());
    }

    public List<CarModel> getFullListOfCars() {
        //henter fulde liste af car
        String DISPLAY_FULL_LIST_OF_CARS_SQL = "SELECT * FROM car";
        //kald af JdbcTemplate med sql og paramter
        List<CarModel> carModels = jdbcTemplate.query(DISPLAY_FULL_LIST_OF_CARS_SQL, new BeanPropertyRowMapper<>(CarModel.class));
        return carModels;
    }

    public void deleteFromListOfCars(int id) {
        //slette sql
        String DELETE_FROM_LIST_OF_CARS_SQL = "DELETE FROM car where id = ?";
        //kald af JdbcTemplate med sql og paramter
        jdbcTemplate.update(DELETE_FROM_LIST_OF_CARS_SQL, id);
    }

    public CarModel GetCarByIDForUpdate(int id) {
        //find SQL
        String GET_CAR_BY_ID_SQL = "SELECT * FROM car WHERE id = ?";
        //returner query-resultat fra JdbcTemplate, med rowmapper som omsætter databaserække til car
        return jdbcTemplate.queryForObject(GET_CAR_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(CarModel.class));
    }

    public void UpdateCarEntryInDatabase(CarModel carModel) {
        //update sql
        String UPDATE_CAR_ENTRy_SQL = "UPDATE car SET serialNumber = ?, carNumber = ?, carModel = ?, carBrand = ?, carYear = ?, monthlyPrice = ?, isLeased = ? WHERE id = ?";
        //update db vha. JdbcTemplate
        jdbcTemplate.update(UPDATE_CAR_ENTRy_SQL, carModel.getSerialNumber(), carModel.getCarNumber(), carModel.getCarModel(), carModel.getCarBrand(), carModel.getCarYear(), carModel.getCarYear(), carModel.isLeased(), carModel.getId());
    }
}
