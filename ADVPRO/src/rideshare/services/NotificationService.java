package rideshare.services;


import rideshare.models.Driver;
import rideshare.models.Passenger;
import rideshare.models.Ride;

public class NotificationService  {
    public static void notifyRideBooked(Passenger passenger, Driver driver, Ride ride) {
        System.out.println("Ride booked: " + ride.getRideId() + " for passenger: " + passenger.getName() + " with driver: " + driver.getName());
    }

    public static void notifyRideCompleted(Passenger passenger, Ride ride) {
        System.out.println("Ride completed: " + ride.getRideId() + " for passenger: " + passenger.getName());
    }

    public static void notifyRideCanceled(Passenger passenger, Ride ride) {
        System.out.println("Ride canceled: " + ride.getRideId() + " for passenger: " + passenger.getName());
    }
}
