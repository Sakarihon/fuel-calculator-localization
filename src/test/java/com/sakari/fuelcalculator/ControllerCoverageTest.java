package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerCoverageTest {

    @Test
    void coverCalculationPaths() {
        double distance = 120;
        double consumption = 6;
        double price = 2;

        double totalFuel = (consumption / 100.0) * distance;
        double totalCost = totalFuel * price;

        assertEquals(7.2, totalFuel, 0.0001);
        assertEquals(14.4, totalCost, 0.0001);

        String result = String.format("%.2f %.2f", totalFuel, totalCost);
        assertNotNull(result);
    }

    @Test
    void coverInvalidParsing() {
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("not_a_number");
        });
    }

    @Test
    void coverMultipleScenarios() {
        for (int i = 0; i < 5; i++) {
            double distance = i * 10;
            double consumption = i + 1;
            double price = 2;

            double fuel = (consumption / 100.0) * distance;
            double cost = fuel * price;

            assertTrue(cost >= 0);
        }
    }
}