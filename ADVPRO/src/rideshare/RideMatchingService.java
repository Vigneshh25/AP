package rideshare;

import java.util.ArrayList;
import java.util.List;

public class RideMatchingService {
    private PathService pathService = new PathService();

    public List<Driver> findMatchingDrivers(RideRequest request, List<Driver> drivers) {
        List<Driver> matchingDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable() && pathService.isPathOverlap(driver.getLocation(), driver.getLocation(), request.getStartLocation(), request.getEndLocation())) {
                matchingDrivers.add(driver);
            }
        }
        return matchingDrivers;
    }

    public void assignDriver(RideRequest request, Driver driver) {
        request.setStatus("ASSIGNED");
        driver.setAvailability(false);
        // Create a new Ride object, update status, etc.
    }
}
