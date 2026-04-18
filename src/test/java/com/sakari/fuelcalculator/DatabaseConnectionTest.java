package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testConnectionAttempt() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            assertNotNull(conn);
            conn.close();
        } catch (Exception e) {
            fail("Connection should succeed in test environment: " + e.getMessage());
        }
    }

    @Test
    void testStaticInitialization() {
        assertDoesNotThrow(() -> {
            try {
                DatabaseConnection.getConnection();
            } catch (Exception ignored) {}
        });
    }

    @Test
    void testMultipleConnections() {
        assertDoesNotThrow(() -> {
            Connection conn1 = DatabaseConnection.getConnection();
            Connection conn2 = DatabaseConnection.getConnection();
            assertNotNull(conn1);
            assertNotNull(conn2);
            conn1.close();
            conn2.close();
        });
    }

    @Test
    void testConnectionProperties() {
        assertDoesNotThrow(() -> {
            Connection conn = DatabaseConnection.getConnection();
            assertFalse(conn.isClosed());
            conn.close();
            assertTrue(conn.isClosed());
        });
    }
}