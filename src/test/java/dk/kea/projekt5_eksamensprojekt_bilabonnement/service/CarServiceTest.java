package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;

import dk.kea.projekt5_eksamensprojekt_bilabonnement.model.CarModel;
import dk.kea.projekt5_eksamensprojekt_bilabonnement.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    //Vi bruger mock/mockito til at isolere testen, og sørge for at det kun er metoden fra servicen der bliver kaldt.
    //ChatGPT har hjulpet med til at implementere @Mock og @InjectMocks, samt importen af assertEquals.

    // Mock annotationen opretter en kopi af carRepository. Den efterligner (mocker) objektet.
    @Mock
    private CarRepository carRepository;

    // InjectMocks opretter en instans af Carservice, og injecter carrepository over i.
    @InjectMocks CarService carService;

    //BeforeEach initialisrer mocksne og bliver udført før hver test.
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTotalMonthlyPriceForLeasedCars() {
        //Arrange

        //Vi opretter tre Carmodeller og deres månedlige pris bliver sat.
        CarModel car1 = new CarModel();
        car1.setMonthly_price(200);

        CarModel car2 = new CarModel();
        car2.setMonthly_price(100);

        //De bliver herefter lagt ned i en liste der repræsenterer leaesede biler.
        List<CarModel> leasedCars = Arrays.asList(car1,car2);

        //behavior af getLeasedCars() metoden af  det "mocked" carRepository returnerer listen af leasede biler når den bliver kaldt.

        when(carRepository.getLeasedCars()).thenReturn(leasedCars);

        //Act

        //Vi kalder her metoden der udregner prisen for leasede biler.
        double totalMonthlyPrice = carService.calculateTotalMonthlyPriceForLeasedCars();

        //assert

        //Her sættes der hvad vi forventer at den samlede pris vil blive for de leasede biler vi har oprettet. Med en 0.01 tolerance.
        assertEquals(300, totalMonthlyPrice, 0.01);
    }
}
