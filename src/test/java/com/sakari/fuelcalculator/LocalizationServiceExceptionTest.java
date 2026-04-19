package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class LocalizationServiceExceptionTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Test
    void loadStrings_DatabaseDown_TriggersCatch() {
        mysql.stop();
        LocalizationService service = new LocalizationService();
        assertDoesNotThrow(() -> service.loadStrings("en"));
        assertTrue(service.getAllKeys().isEmpty());
        mysql.start();
    }
}