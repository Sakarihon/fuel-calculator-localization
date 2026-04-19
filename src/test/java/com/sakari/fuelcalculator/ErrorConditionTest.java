package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErrorConditionTest {

    @Test
    void calculationService_SaveThrowsSQLException() throws SQLException {
        CalculationService service = new CalculationService();
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);

        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
        doThrow(new SQLException("Simulated database error"))
                .when(mockStmt).executeUpdate();

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            assertDoesNotThrow(() ->
                    service.saveCalculation(100, 5, 2, 5, 10, "en")
            );
        }
    }

    @Test
    void localizationService_LoadThrowsSQLException() throws SQLException {
        LocalizationService service = new LocalizationService();
        Connection mockConn = mock(Connection.class);

        when(mockConn.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated database error"));

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            assertDoesNotThrow(() -> service.loadStrings("en"));
            assertEquals(0, service.getAllKeys().size());
        }
    }

    @Test
    void localizationService_GetStringWithNullKey() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en");
        String result = service.getString(null);
        assertTrue(result.contains("MISSING"));
    }

   }