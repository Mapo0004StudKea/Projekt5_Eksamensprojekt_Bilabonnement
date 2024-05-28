package dk.kea.projekt5_eksamensprojekt_bilabonnement.service;


   import org.junit.jupiter.api.Test;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.context.SpringBootTest;

   import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//ChatGPT har hjulpet med at sætte testene op, samt blevet brugt til at kontrollere at dataene er sat korrekt ind.

@SpringBootTest
    public class LeasingServiceTest {

    // Opretter en instans af LeasingService, som vi vil teste
    @Autowired LeasingService leasingService;

        @Test
        public void testIsLimitedExceeds150Days() {
            // Definerer start- og slutdatoer for leasingperioden
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.of(2024, 6, 1);

            // Kalder checkLeasingDate-metoden med en begrænset leasingperiode på mere end 150 dage
            String result = leasingService.checkLeasingDate(start, end, false, true);
            assertEquals("For en begrænset leasingaftale, kan du kun leje 150 dage", result);
        }

        @Test
        public void testIsUnlimitedLessThan90Days() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.of(2024, 2, 15);

            // Kalder checkLeasingDate-metoden med en ubegrænset leasingperiode på mindre end 90 dage
            String result = leasingService.checkLeasingDate(start, end, true, false);
            assertEquals("For en ubegrænset lejeaftale, skal længden være minimum 3 måneder.", result);
        }

        @Test
        public void testIsLimitedWithin150Days() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.of(2024, 5, 1);

            // Kalder checkLeasingDate-metoden med en begrænset leasingperiode på under 150 dage
            String result = leasingService.checkLeasingDate(start, end, false, true);

            // Kontrollerer, at resultatet er null, da leasingperioden er gyldig for begrænset leasing
            assertEquals(null, result);
        }

        @Test
        public void testIsUnlimitedMoreThan90Days() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.of(2024, 4, 1);
            String result = leasingService.checkLeasingDate(start, end, true, false);

            // Kontrollerer, at resultatet er null, da leasingperioden er gyldig for ubegrænset leasing
            assertEquals(null, result);
        }

        @Test
        public void testLeaseCannotBeBothLimitedAndUnlimited() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.of(2024, 4, 1);

            // Kalder checkLeasingDate-metoden med både begrænset og ubegrænset flag sat til true
            String result = leasingService.checkLeasingDate(start, end, true, true);

            // Kontrollerer, at resultatet matcher den forventede fejlmeddelelse om, at leasing ikke kan være både begrænset og ubegrænset
            assertEquals("En leasingaftale kan ikke være både begrænset og ubegrænset.", result);
        }

    }

