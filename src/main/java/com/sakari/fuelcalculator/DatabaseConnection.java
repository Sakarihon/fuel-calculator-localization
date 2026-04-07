package com.sakari.fuelcalculator;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();

            InputStream input = DatabaseConnection.class
                    .getClassLoader()
                    .getResourceAsStream("database.properties");

            if (input == null) {
                throw new RuntimeException("database.properties not found in resources!");
            }

            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                user,
                password
        );
    }
}