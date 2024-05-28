package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarsAndLeasingModels;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.LeasingModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.LeasingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DashServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private LeasingRepository leasingRepository;

    @InjectMocks
    private DashService dashService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListOfCarsNearEndLeasing() {
        // Arrange
        LocalDate today = LocalDate.now();
        int daysThreshold = 30;

        // Opretter mock-objekter for biler og leasingaftaler
        CarModel car1 = new CarModel(1, "Car1", true);
        CarModel car2 = new CarModel(2, "Car2", true);
        CarModel car3 = new CarModel(3, "Car3", false);

        List<CarModel> carList = Arrays.asList(car1, car2, car3);

        LeasingModel leasing1 = new LeasingModel(1, 1, today.plusDays(20));
        LeasingModel leasing2 = new LeasingModel(2, 2, today.plusDays(40));
        LeasingModel leasing3 = new LeasingModel(3, 1, today.plusDays(10));

        // Konfigurerer mock-objekternes metoder til at returnere forventede værdier
        when(carRepository.getFullListOfCars()).thenReturn(carList);
        when(leasingRepository.getListOfLeasingContractsById(1)).thenReturn(Arrays.asList(leasing1, leasing3));
        when(leasingRepository.getListOfLeasingContractsById(2)).thenReturn(Collections.singletonList(leasing2));
        when(leasingRepository.getListOfLeasingContractsById(3)).thenReturn(Collections.emptyList());

        // Act
        // Kalder metoden, der skal testes
        CarsAndLeasingModels result = dashService.listOfCarsNearEndLeasing(daysThreshold);

        // Assert
        // Tester om resultatet fra metoden stemmer overens med forventningerne
        assertEquals(1, result.getCars().size());
        assertEquals(1, result.getCars().size());
        assertEquals(car1, result.getCars().get(0));
        assertEquals(leasing3, result.getLeasingModels().get(0));
    }
}

