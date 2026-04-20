package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerLogicTest {

    @Test
    void testFullFuelCalculationFlow() {
        double distance = 150;
        double consumption = 6;
        double price = 2;

        double totalFuel = (consumption / 100.0) * distance;
        double totalCost = totalFuel * price;

        assertEquals(9.0, totalFuel, 0.001);
        assertEquals(18.0, totalCost, 0.001);
    }

    @Test
    void testInvalidInputHandlingSimulation() {
        String input = "abc";
        boolean isNumber = input.matches("-?\\d+(\\.\\d+)?");
        assertFalse(isNumber);
    }
}