package Taxi.versions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class Taxi {
    private int id;
    private char currentSpot;
    private int freeTime;
    private int totalEarnings;
    private List<Trip> trips;

    public Taxi(int id) {
        this.id = id;
        this.currentSpot = 'A';
        this.freeTime = 6;
        this.totalEarnings = 0;
        this.trips = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public char getCurrentSpot() {
        return currentSpot;
    }

    public boolean isFree(int pickupTime, char pickupPoint) {
        return freeTime <= pickupTime && Math.abs(currentSpot - pickupPoint) <= pickupTime - freeTime;
    }


    public void book(int customerID, char pickupPoint, char dropPoint, int pickupTime) {
        int distance = Math.abs(dropPoint - pickupPoint);
        int travelTime = distance * 60;  // Travel time in minutes (60 mins per point)
        int amount = (distance - 5) * 10 + 100;
        int dropTime = pickupTime + travelTime / 60;  // Convert travel time to hours
        trips.add(new Trip(customerID, pickupPoint, dropPoint, pickupTime, dropTime, amount));
        freeTime = dropTime;
        currentSpot = dropPoint;
        totalEarnings += amount;
    }

    public void displayDetails() {
        System.out.println("Taxi-" + id + " Total Earnings: Rs. " + totalEarnings);
        System.out.println("BookingID\tCustomerID\tFrom\tTo\tPickupTime\tDropTime\tAmount");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
        System.out.println("-----------------------------------------------");
    }
}
