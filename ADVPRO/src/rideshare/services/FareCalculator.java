package rideshare.services;

import rideshare.models.Location;

public class FareCalculator {
    private static final double BASE_FARE = 50.0;
    private static final double COST_PER_KM = 10.0;

    public static double calculateFare(Location origin, Location destination) {
        double distance = calculateDistance(origin, destination);
        return BASE_FARE + (distance * COST_PER_KM);
    }

    private static double calculateDistance(Location origin, Location destination) {
        double latDiff = Math.abs(origin.getLatitude() - destination.getLatitude());
        double lonDiff = Math.abs(origin.getLongitude() - destination.getLongitude());
        // Dummy implementation for distance calculation
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }
}
