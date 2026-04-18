package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
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
}