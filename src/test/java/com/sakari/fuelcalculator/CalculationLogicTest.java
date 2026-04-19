package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationLogicTest {

    @Test
    void testFuelCalculation() {
        double distance = 100;
        double consumption = 5;
        double fuel = (consumption / 100) * distance;
        assertEquals(5.0, fuel);
    }

    @Test
    void testCostCalculation() {
        double fuel = 10;
        double price = 2;
        double cost = fuel * price;
        assertEquals(20, cost);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 5, 5.0",
            "200, 8, 16.0",
            "50, 4, 2.0",
            "0, 10, 0.0",
            "150, 7.5, 11.25",
            "80, 4, 3.2",
            "120, 6, 7.2",
            "250, 10, 25.0"
    })
    void testFuelCalculationMultiple(double distance, double consumption, double expected) {
        double fuel = (consumption / 100) * distance;
        assertEquals(expected, fuel, 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 20.0",
            "5, 1.5, 7.5",
            "0, 2, 0.0",
            "7, 3.2, 22.4"
    })
    void testCostCalculationMultiple(double fuel, double price, double expected) {
        double cost = fuel * price;
        assertEquals(expected, cost, 0.001);
    }

    @Test
    void testZeroValues() {
        assertEquals(0.0, (0 / 100.0) * 0);
        assertEquals(0.0, 0 * 0);
    }
}