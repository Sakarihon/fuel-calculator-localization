package com.sakari.fuelcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.mockito.Mockito.*;

class CalculationServiceTest {
    private CalculationService service;
    private Connection mockConn;
    private PreparedStatement mockStmt;

    @BeforeEach
    void setUp() throws SQLException {
        service = new CalculationService();
        mockConn = mock(Connection.class);
        mockStmt = mock(PreparedStatement.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
    }

    @Test
    void saveCalculation_Success() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            service.saveCalculation(100, 5, 2, 5, 10, "en");
            verify(mockStmt).executeUpdate();
        }
    }

    @Test
    void saveCalculation_SQLException() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareStatement(anyString())).thenThrow(new SQLException("DB error"));
            assertDoesNotThrow(() -> service.saveCalculation(100, 5, 2, 5, 10, "en"));
        }
    }

    @Test
    void saveCalculation_ZeroValues() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            service.saveCalculation(0, 0, 0, 0, 0, "en");
            verify(mockStmt).executeUpdate();
        }
    }
}