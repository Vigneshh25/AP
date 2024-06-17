package rideshare.utils;



import rideshare.models.Driver;
import rideshare.models.Ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Map<String, Ride> rideDatabase;
    private List<Driver> driverDatabase;

    private DatabaseManager() {
        rideDatabase = new HashMap<>();
        driverDatabase = new ArrayList<>();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void saveRide(Ride ride) {
        rideDatabase.put(ride.getRideId(), ride);
    }

    public List<Driver> getAvailableDrivers() {
        return driverDatabase.stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }

    public void addDriver(Driver driver) {
        driverDatabase.add(driver);
    }

    public void updateDriver(Driver driver) {
        for (int i = 0; i < driverDatabase.size(); i++) {
            if (driverDatabase.get(i).getId().equals(driver.getId())) {
                driverDatabase.set(i, driver);
                break;
            }
        }
    }

    public void updateRide(Ride ride) {
        rideDatabase.put(ride.getRideId(), ride);
    }
}

