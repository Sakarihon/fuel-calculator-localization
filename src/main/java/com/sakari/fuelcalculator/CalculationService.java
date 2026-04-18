package com.sakari.fuelcalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalculationService {

    public void saveCalculation(double distance, double consumption,
                                double price, double totalFuel,
                                double totalCost, String language) {

        String sql = "INSERT INTO calculation_records " +
                "(distance, consumption, price, total_fuel, total_cost, language) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, distance);
            stmt.setDouble(2, consumption);
            stmt.setDouble(3, price);
            stmt.setDouble(4, totalFuel);
            stmt.setDouble(5, totalCost);
            stmt.setString(6, language);

            stmt.executeUpdate();

            System.out.println("Saved to database!");

        } catch (SQLException e) {
            System.err.println("Database save failed: " + e.getMessage());
        }
    }
}