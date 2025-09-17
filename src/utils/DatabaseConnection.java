package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bankdb";
    private static final String USER = "yasser";
    private static final String PASSWORD = "yourpassword";


    public static Connection getConnection() throws SQLException {
        try {
            // PostgreSQL driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found!", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

