package Taxi.versions;

import java.util.List;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class Booking {
    public static void bookTaxi(int customerID, char pickupPoint, char dropPoint, int pickupTime, List<Taxi> freeTaxis) {
        Taxi bookedTaxi = findNearestTaxi(pickupPoint, freeTaxis);
        if (bookedTaxi != null) {
            bookedTaxi.book(customerID, pickupPoint, dropPoint, pickupTime);
            System.out.println("Taxi-" + bookedTaxi.getId() + " is allotted");
        } else {
            System.out.println("No free taxi available at the moment. Booking rejected.");
        }
    }

    private static Taxi findNearestTaxi(char pickupPoint, List<Taxi> freeTaxis) {
        int minDistance = Integer.MAX_VALUE;
        Taxi nearestTaxi = null;

        for (Taxi taxi : freeTaxis) {
            int distance = Math.abs(taxi.getCurrentSpot() - pickupPoint);
            if (distance < minDistance) {
                nearestTaxi = taxi;
                minDistance = distance;
            }
        }

        return nearestTaxi;
    }
}
