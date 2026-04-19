package com.sakari.fuelcalculator;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {
    private Controller controller;
    private TextField txtDistance, txtConsumption, txtPrice;
    private Label lblResult;
    private Button btnCalculate;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new Controller();
        txtDistance = new TextField();
        txtConsumption = new TextField();
        txtPrice = new TextField();
        lblResult = new Label();
        btnCalculate = new Button();

        controller.txtDistance = txtDistance;
        controller.txtConsumption = txtConsumption;
        controller.txtPrice = txtPrice;
        controller.lblResult = lblResult;
        controller.btnCalculate = btnCalculate;
        controller.lblDistance = new Label();
        controller.lblConsumption = new Label();
        controller.lblPrice = new Label();
        controller.lblResult = lblResult;

        controller.initialize();
    }

    @Test
    void handleCalculate_ValidInput() throws Exception {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            Connection mockConn = mock(Connection.class);
            PreparedStatement mockStmt = mock(PreparedStatement.class);
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

            txtDistance.setText("100");
            txtConsumption.setText("5");
            txtPrice.setText("2");

            controller.handleCalculate();

            assertTrue(lblResult.getText().contains("Fuel: 5.00"));
        }
    }

    @Test
    void handleCalculate_InvalidInput() {
        txtDistance.setText("abc");
        controller.handleCalculate();
        assertTrue(lblResult.getText().contains("MISSING")); // koska localization ei lataudu kunnolla
    }

    @Test
    void setEnglish_ChangesLanguage() throws Exception {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            Connection mockConn = mock(Connection.class);
            PreparedStatement mockStmt = mock(PreparedStatement.class);
            ResultSet mockRs = mock(ResultSet.class);
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
            when(mockStmt.executeQuery()).thenReturn(mockRs);
            when(mockRs.next()).thenReturn(true, false);
            when(mockRs.getString("key")).thenReturn("distance.label");
            when(mockRs.getString("value")).thenReturn("Distance EN");

            controller.setEnglish();
            assertEquals("Distance EN", controller.lblDistance.getText());
        }
    }

    @Test
    void setFrench_ChangesLanguage() throws Exception {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {
            Connection mockConn = mock(Connection.class);
            PreparedStatement mockStmt = mock(PreparedStatement.class);
            ResultSet mockRs = mock(ResultSet.class);
            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
            when(mockStmt.executeQuery()).thenReturn(mockRs);
            when(mockRs.next()).thenReturn(true, false);
            when(mockRs.getString("key")).thenReturn("distance.label");
            when(mockRs.getString("value")).thenReturn("Distance FR");

            controller.setFrench();
            assertEquals("Distance FR", controller.lblDistance.getText());
        }
    }
}