package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorLogicTest {

    @Test
    void calculateFuel_ValidInput() {
        assertEquals(5.0, FuelCalculatorLogic.calculateFuel(100, 5));
    }

    @Test
    void calculateCost_ValidInput() {
        assertEquals(10.0, FuelCalculatorLogic.calculateCost(5, 2));
    }

    @ParameterizedTest
    @CsvSource({
            "100, 5, 2, 5.0, 10.0",
            "200, 8, 1.5, 16.0, 24.0",
            "0, 10, 3, 0.0, 0.0"
    })
    void calculateBoth(double distance, double consumption, double price,
                       double expectedFuel, double expectedCost) {
        double[] result = FuelCalculatorLogic.calculateBoth(distance, consumption, price);
        assertEquals(expectedFuel, result[0], 0.001);
        assertEquals(expectedCost, result[1], 0.001);
    }
}