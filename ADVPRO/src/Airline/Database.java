package Airline;

public class Database {
    private static Database instance;

    private Database() {
        // Initialize connection
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void query(String sql) {
        // Execute query
    }
}
