package org.example.database;

import org.example.out.Output;
import org.example.properties.AppProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = AppProperties.getProperty("url");
    private static final String USER = AppProperties.getProperty("user");
    private static final String PASSWORD = AppProperties.getProperty("password");

    private DbConnection() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception " + e.getMessage());
        }
        return connection;
    }
}
