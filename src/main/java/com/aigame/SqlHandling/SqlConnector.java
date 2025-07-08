package com.aigame.SqlHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/wumpusGame";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ajay@2005";

    public static Connection makeConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
