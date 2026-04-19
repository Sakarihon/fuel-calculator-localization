package com.sakari.fuelcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.*;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocalizationServiceTest {
    private LocalizationService service;
    private Connection mockConn;
    private PreparedStatement mockStmt;
    private ResultSet mockRs;

    @BeforeEach
    void setUp() throws SQLException {
        service = new LocalizationService();
        mockConn = mock(Connection.class);
        mockStmt = mock(PreparedStatement.class);
        mockRs = mock(ResultSet.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
    }

    @Test
    void loadStrings_Success() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockRs.next()).thenReturn(true, false);
            when(mockRs.getString("key")).thenReturn("test.key");
            when(mockRs.getString("value")).thenReturn("Test Value");

            service.loadStrings("en");
            assertEquals("en", service.getCurrentLanguage());
            assertEquals("Test Value", service.getString("test.key"));
        }
    }

    @Test
    void loadStrings_SQLException() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareStatement(anyString())).thenThrow(new SQLException("DB down"));
            assertDoesNotThrow(() -> service.loadStrings("en"));
        }
    }

    @Test
    void getString_MissingKey() {
        assertEquals("MISSING: fake.key", service.getString("fake.key"));
    }

    @Test
    void getAllKeys_InitiallyEmpty() {
        Map<String, String> keys = service.getAllKeys();
        assertNotNull(keys);
        assertTrue(keys.isEmpty());
    }

    @Test
    void getAllKeys_AfterLoad() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockRs.next()).thenReturn(true, false);
            when(mockRs.getString("key")).thenReturn("a");
            when(mockRs.getString("value")).thenReturn("A");
            service.loadStrings("en");
            assertEquals(1, service.getAllKeys().size());
        }
    }

    @Test
    void setLanguageTwice_CacheCleared() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockRs.next()).thenReturn(true, false);
            when(mockRs.getString("key")).thenReturn("key1");
            when(mockRs.getString("value")).thenReturn("val1");
            service.loadStrings("en");
            service.loadStrings("fi");
            verify(mockStmt, times(2)).setString(1, anyString());
        }
    }
}