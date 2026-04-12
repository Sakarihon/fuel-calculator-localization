package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainCoverageTest {

    @Test
    void testMainExecution() {
        assertDoesNotThrow(() -> {
            String[] args = {};
            Main.main(args);
        });
    }
}