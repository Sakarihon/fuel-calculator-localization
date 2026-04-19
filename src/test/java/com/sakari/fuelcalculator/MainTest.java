package com.sakari.fuelcalculator;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @Test
    void testMainDoesNotCrash() {
        assertDoesNotThrow(() -> {
            Main.main(new String[]{});
        });
    }
}