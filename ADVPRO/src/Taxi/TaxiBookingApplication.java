package Taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

class TaxiService {
    private List<Taxi> taxis;


    public TaxiService(int numTaxis) {
        taxis = new ArrayList<>();
        for (int i = 0; i < numTaxis; i++) {
            taxis.add(new Taxi(i + 1));
        }
    }

    public List<Taxi> getFreeTaxis(int pickupTime, char pickupPoint) {
        List<Taxi> freeTaxis = new ArrayList<>();
        for (Taxi taxi : taxis) {
            if (taxi.isFree(pickupTime, pickupPoint)) {
                freeTaxis.add(taxi);
            }
        }
        return freeTaxis;
    }

    public void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            taxi.displayDetails();
        }
    }
}

public class TaxiBookingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxiService taxiService = new TaxiService(4);
        int customerID = 1;

        while (true) {
            System.out.println("1. Book Taxi");
            System.out.println("2. Display Taxi Details");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Pickup point: ");
                    char pickupPoint = scanner.next().charAt(0);
                    System.out.print("Enter Drop point: ");
                    char dropPoint = scanner.next().charAt(0);
                    System.out.print("Enter Pickup time: ");
                    int pickupTime = scanner.nextInt();

                    Booking.bookTaxi(customerID, pickupPoint, dropPoint, pickupTime, taxiService.getFreeTaxis(pickupTime, pickupPoint));
                    customerID++;
                    break;

                case 2:
                    taxiService.displayTaxiDetails();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

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

class Trip {
    private int bookingID;
    private int customerID;
    private char from;
    private char to;
    private int pickupTime;
    private int dropTime;
    private int amount;

    public Trip(int customerID, char from, char to, int pickupTime, int dropTime, int amount) {
        this.bookingID = customerID;
        this.customerID = customerID;
        this.from = from;
        this.to = to;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return bookingID + "\t" + customerID + "\t" + from + "\t" + to + "\t" + pickupTime + "\t" + dropTime + "\t" + amount;
    }
}
