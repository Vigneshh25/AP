package rideshare;

import java.util.ArrayList;
import java.util.List;

public class RideManager {
    private List<RideRequest> rideRequests = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private RideMatchingService matchingService = new RideMatchingService();

    public RideRequest createRideRequest(Rider rider, Location startLocation, Location endLocation) {
        RideRequest request = new RideRequest(rider, startLocation, endLocation);
        rideRequests.add(request);
        return request;
    }

    public synchronized void acceptRide(Driver driver, RideRequest request) {
        if (request.getStatus().equals("PENDING")) {
            matchingService.assignDriver(request, driver);
            rides.add(new Ride(request.getRider(), driver, request.getStartLocation(), request.getEndLocation()));
        }
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void processRequests() {
        for (RideRequest request : rideRequests) {
            if (request.getStatus().equals("PENDING")) {
                List<Driver> matchingDrivers = matchingService.findMatchingDrivers(request, drivers);
                if (!matchingDrivers.isEmpty()) {
                    // Assign the first matching driver (could be improved with better selection logic)
                    acceptRide(matchingDrivers.get(0), request);
                }
            }
        }
    }
}
