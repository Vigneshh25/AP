package TollPayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TollGate {
    private Map<String, Toll> tolls;
    private Map<String, Vehicle> vehicles;

    public TollGate() {
        this.tolls = new HashMap<String, Toll>();
        this.vehicles = new HashMap<String, Vehicle>();
    }

    public void addToll(String name) {
        this.tolls.put(name, new Toll(name));
    }

    public void addChargingScheme(String tollName, String vehicleType, int amount) {
        Toll toll = tolls.get(tollName);
        toll.addChargingScheme(vehicleType, amount);
    }

    public void addVehicle(String type, String number, boolean isVip) {
        this.vehicles.put(number, new Vehicle(type, number, isVip));
    }

    public void addJourney(String vehicleNumber, String start, String destination, ArrayList<String> tollsPassed) {
        Vehicle vehicle = vehicles.get(vehicleNumber);
        ArrayList<Toll> tolls = new ArrayList<Toll>();
        int amountPaid = 0;
        for (String tollName : tollsPassed) {
            Toll toll = this.tolls.get(tollName);
            int tollAmount = toll.getChargingScheme().get(vehicle.getType());
            if (vehicle.isVip()) {
                tollAmount = (int) (tollAmount * 0.8);
            }
            amountPaid += tollAmount;
            tolls.add(toll);
            toll.addVehiclePassed(vehicle);
        }
        Journey journey = new Journey(start, destination, tolls, amountPaid);
        vehicle.addJourney(journey);
    }

    public int getTotalTollPaid(String vehicleNumber) {
        Vehicle vehicle = vehicles.get(vehicleNumber);
        int totalAmount = 0;
        for (Journey journey : vehicle.getJourneys()) {
            totalAmount += journey.getAmountPaid();
        }
        return totalAmount;
    }

    public void displayTollDetails(String tollName) {
        Toll toll = tolls.get(tollName);
        System.out.println("Toll Name: " + toll.getName());
        System.out.println("Vehicles Passed: ");
        int totalAmount = 0;
        for (Vehicle vehicle : toll.getVehiclesPassed()) {
            System.out.println("Vehicle Number: " + vehicle.getNumber() + ", Amount Paid: " + toll.getChargingScheme().get(vehicle.getType()));
            totalAmount += toll.getChargingScheme().get(vehicle.getType());
        }
        System.out.println("Total Amount Charged: " + totalAmount);
    }

    public void displayVehicleDetails(String vehicleNumber) {
        Vehicle vehicle = vehicles.get(vehicleNumber);
        System.out.println("Vehicle Number: " + vehicle.getNumber());
        System.out.println("Journeys: ");
        int totalAmount = 0;
        for (Journey journey : vehicle.getJourneys()) {
            System.out.println("Start: " + journey.getStart() + ", Destination: " + journey.getDestination() + ", Tolls Passed: " + journey.getTollsPassed().toString() + ", Amount Paid: " + journey.getAmountPaid());
            totalAmount += journey.getAmountPaid();
        }
        System.out.println("Total Amount Paid: " + totalAmount);
    }public void findShortestRouteAndAmount(String start, String destination, ArrayList<String> allTolls) {
// Assume the highway is a circular path
        int minDistance = Integer.MAX_VALUE;
        ArrayList<String> tollsPassed = new ArrayList<String>();
        int amountPaid = 0;
        for (int i = 0; i < allTolls.size(); i++) {
            if (allTolls.get(i).equals(start)) {
                for (int j = i; j < allTolls.size(); j++) {
                    if (allTolls.get(j).equals(destination)) {
                        if (j - i < minDistance) {
                            minDistance = j - i;
                            tollsPassed.clear();
                            amountPaid = 0;
                            for (int k = i; k <= j; k++) {
                                tollsPassed.add(allTolls.get(k));
                                amountPaid += tolls.get(allTolls.get(k)).getChargingScheme().get("Car");
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Shortest Route: " + tollsPassed.toString());
        System.out.println("Amount Paid: " + amountPaid);
    }
}
