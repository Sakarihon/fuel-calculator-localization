package com.sakari.fuelcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    private CalculationService service;

    @BeforeEach
    void setUp() {
        service = new CalculationService();
    }

    @Test
    void testSaveCalculationWithValidData() {
        assertDoesNotThrow(() ->
                service.saveCalculation(100, 5, 2, 5, 10, "en")
        );
    }

    @Test
    void testSaveCalculationWithZeroValues() {
        assertDoesNotThrow(() ->
                service.saveCalculation(0, 0, 0, 0, 0, "en")
        );
    }

    @Test
    void testSaveCalculationWithDifferentLanguage() {
        assertDoesNotThrow(() ->
                service.saveCalculation(200, 8, 3, 16, 48, "fi")
        );
    }

    @Test
    void testSaveCalculationWithNegativeValues() {
        assertDoesNotThrow(() ->
                service.saveCalculation(-100, -5, -2, -5, -10, "en")
        );
    }

    @Test
    void testMultipleSaveCalls() {
        for (int i = 1; i <= 5; i++) {
            int v = i * 10;
            assertDoesNotThrow(() ->
                    service.saveCalculation(v, v/2.0, 1.5, v/2.0, v*1.5, "en")
            );
        }
    }
}