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
}