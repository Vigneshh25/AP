package Airtraffic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Flight {
    String type;
    int weight;
    int timeToHalt;

    public Flight(String type, int weight) {
        this.type = type;
        this.weight = weight;

        // Calculate time to halt based on weight
        if (weight >= 15) {
            timeToHalt = 30;
        } else if (weight >= 10) {
            timeToHalt = 30 - (weight - 10) * 3;
        } else {
            timeToHalt = 30 - (weight - 10) * 2;
        }
    }
}

class Runway {
    String name;
    boolean engaged;
    long endTime;

    public Runway(String name) {
        this.name = name;
        this.engaged = false;
        this.endTime = System.currentTimeMillis();
    }
}

public class AirTrafficControl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Runway> runways = new HashMap<>();
        Queue<Flight> takeoffQueue = new LinkedList<>();
        Queue<Flight> landingQueue = new LinkedList<>();
        Queue<Flight> emergencyLandingQueue = new LinkedList<>();

        // Initialize runways
        runways.put("r1", new Runway("r1"));
        runways.put("r2", new Runway("r2"));
        runways.put("r3", new Runway("r3"));
        runways.put("r4", new Runway("r4"));

        while (true) {
            System.out.println("Air Traffic Control System");
            System.out.println("1. Request Takeoff");
            System.out.println("2. Request Landing");
            System.out.println("3. Request Emergency Landing");
            System.out.println("4. Show Runway Status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    requestTakeoff(runways, takeoffQueue, scanner);
                    break;
                case 2:
                    requestLanding(runways, landingQueue, scanner);
                    break;
                case 3:
                    requestEmergencyLanding(runways, emergencyLandingQueue, scanner);
                    break;
                case 4:
                    showRunwayStatus(runways);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void requestTakeoff(HashMap<String, Runway> runways, Queue<Flight> takeoffQueue, Scanner scanner) {
        System.out.print("Enter the plane type (ATR, Airbus, Boeing, Cargo): ");
        String type = scanner.next();
        System.out.print("Enter the plane weight (in tonnes): ");
        int weight = scanner.nextInt();

        Flight flight = new Flight(type, weight);

        System.out.println("Request for takeoff for " + type + " with weight in " + allocateRunway(runways) + ".");
        System.out.println("The plane will take off in " + flight.timeToHalt + " seconds.");

        Runway runway = allocateRunway(runways);
        runway.engaged = true;
        runway.endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(flight.timeToHalt); // 10 seconds for takeoff

        releaseRunway(runway, runways);
    }

    private static void requestLanding(HashMap<String, Runway> runways, Queue<Flight> landingQueue, Scanner scanner) {
        System.out.print("Enter the plane type (ATR, Airbus, Boeing, Cargo): ");
        String type = scanner.next();
        System.out.print("Enter the plane weight (in tonnes): ");
        int weight = scanner.nextInt();

        Flight flight = new Flight(type, weight);

        System.out.println("Request for landing for " + type + " with weight in " + allocateRunway(runways) + ".");
        System.out.println("Touchdown will happen in " + flight.timeToHalt + " seconds.");

        Runway runway = allocateRunway(runways);
        runway.engaged = true;
        runway.endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(flight.timeToHalt + 5); // Halt time + 5 seconds

        releaseRunway(runway, runways);
    }

    private static void requestEmergencyLanding(HashMap<String, Runway> runways, Queue<Flight> emergencyLandingQueue, Scanner scanner) {
        System.out.print("Enter the plane type (ATR, Airbus, Boeing, Cargo): ");
        String type = scanner.next();
        System.out.print("Enter the plane weight (in tonnes): ");
        int weight = scanner.nextInt();

        Flight flight = new Flight(type, weight);

        System.out.println("Request for emergency landing for " + type + " with weight in " + allocateRunway(runways) + ".");
        System.out.println("Touchdown will happen in " + flight.timeToHalt + " seconds.");

        Runway runway = allocateRunway(runways);
        runway.engaged = true;
        runway.endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(flight.timeToHalt + 10); // Halt time + 10 seconds

        releaseRunway(runway, runways);
    }

    private static Runway allocateRunway(HashMap<String, Runway> runways) {
        // Find the first available runway
        for (Runway runway : runways.values()) {
            if (!runway.engaged || runway.endTime <= System.currentTimeMillis()) {
                return runway;
            }
        }

        return null; // No runway available
    }

    private static void releaseRunway(Runway runway, HashMap<String, Runway> runways) {
        // Release the runway after a specified time (10 seconds)
        new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(20)); // Sleep for 10 seconds
                runway.engaged = false; // Release the runway
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static void showRunwayStatus(HashMap<String, Runway> runways) {
        for (Runway runway : runways.values()) {
            System.out.println("Runway " + runway.name + " is " + (runway.engaged ? "engaged" : "available"));
        }
    }
}
