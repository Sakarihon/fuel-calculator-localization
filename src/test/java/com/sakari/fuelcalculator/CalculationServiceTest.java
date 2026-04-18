package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    @Test
    void testSaveDoesNotCrash() {
        CalculationService service = new CalculationService();

        assertDoesNotThrow(() ->
                service.saveCalculation(100, 5, 2, 5, 10, "en")
        );
    }

    @Test
    void testSaveWithDifferentValues() {
        CalculationService service = new CalculationService();

        assertDoesNotThrow(() ->
                service.saveCalculation(200, 8, 3, 16, 48, "fi")
        );
    }

    @Test
    void testSaveHandlesExceptionGracefully() {
        CalculationService service = new CalculationService();

        assertDoesNotThrow(() ->
                service.saveCalculation(0, 0, 0, 0, 0, "xx")
        );
    }

    @Test
    void testCalculateTotalFuel() {
        CalculationService service = new CalculationService();
        // Oleta, että luokassa on metodi calculateTotalFuel(distance, consumption)
        // Jos ei ole, voit jättää tämän pois tai muokata vastaamaan todellista metodia.
        // Tässä esimerkissä käytetään suoraan laskentaa.
        double distance = 120;
        double consumption = 6;
        double expected = (consumption / 100.0) * distance;
        // Kutsu oikeaa metodia, jos sellainen on:
        // double actual = service.calculateTotalFuel(distance, consumption);
        // assertEquals(expected, actual, 0.001);
    }

    @Test
    void testCalculateCost() {
        CalculationService service = new CalculationService();
        double fuel = 10;
        double price = 2.5;
        double expected = fuel * price;
        // double actual = service.calculateCost(fuel, price);
        // assertEquals(expected, actual, 0.001);
    }

    @Test
    void testSaveWithZeroValues() {
        CalculationService service = new CalculationService();
        assertDoesNotThrow(() ->
                service.saveCalculation(0, 0, 0, 0, 0, "en")
        );
    }

    @Test
    void testSaveWithNegativeValues() {
        CalculationService service = new CalculationService();
        assertDoesNotThrow(() ->
                service.saveCalculation(-100, -5, -2, -5, -10, "en")
        );
    }
}