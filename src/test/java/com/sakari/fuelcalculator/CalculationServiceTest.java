package com.sakari.fuelcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculationServiceTest {
    private CalculationService service;

    @BeforeEach
    void setUp() {
        service = new CalculationService();
    }

    @Test
    void testSaveDoesNotCrash() {
        assertDoesNotThrow(() ->
                service.saveCalculation(100, 5, 2, 5, 10, "en")
        );
    }

    @Test
    void testSaveWithDifferentValues() {
        assertDoesNotThrow(() ->
                service.saveCalculation(200, 8, 3, 16, 48, "fi")
        );
    }

    @Test
    void testSaveHandlesExceptionGracefully() {
        assertDoesNotThrow(() ->
                service.saveCalculation(0, 0, 0, 0, 0, "xx")
        );
    }

    @Test
    void testSaveWithZeroValues() {
        assertDoesNotThrow(() ->
                service.saveCalculation(0, 0, 0, 0, 0, "en")
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

    @Test
    void saveCalculation_SQLException_DoesNotCrash() throws SQLException {
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
        doThrow(new SQLException("DB error")).when(mockStmt).executeUpdate();

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            assertDoesNotThrow(() ->
                    service.saveCalculation(1, 2, 3, 4, 5, "en")
            );
        }
    }
}