package BookStore.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            // Load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create the connection object
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "user", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
