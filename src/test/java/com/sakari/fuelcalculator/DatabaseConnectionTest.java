package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void staticInitialization_PropertiesLoaded() {
        assertDoesNotThrow(() -> {
            // Staattinen lohko suoritetaan luokan latautuessa
            Class.forName("com.sakari.fuelcalculator.DatabaseConnection");
        });
    }

    @Test
    void getConnection_ReturnsNonNull() {
        // Jenkinsissä tämä toimii (kontti käynnissä), paikallisesti voi heittää
        try {
            Connection conn = DatabaseConnection.getConnection();
            assertNotNull(conn);
            conn.close();
        } catch (Exception e) {
            fail("Connection should be available: " + e.getMessage());
        }
    }
}