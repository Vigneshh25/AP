package ParkingLot.Parking;

import ParkingLot.Parking.ParkingFloor;
import ParkingLot.Parking.ParkingTicket;
import ParkingLot.Parking.*;

import java.util.*;

class ParkingLot {
    private final Map<Integer, ParkingFloor> floors;
    private static Map<VehicleType, Queue<Vehicle>> waitingQueue;


    public ParkingLot() {
        this.floors = new HashMap<>();
        waitingQueue = new HashMap<>();
        Queue<Vehicle> carWaitingQueue = new LinkedList<>();
        Queue<Vehicle> bikeWaitingQueue = new LinkedList<>();
        Queue<Vehicle> truckWaitingQueue = new LinkedList<>();
        waitingQueue.put(VehicleType.BIKE,bikeWaitingQueue);
        waitingQueue.put(VehicleType.CAR,carWaitingQueue);
        waitingQueue.put(VehicleType.TRUCK,truckWaitingQueue);
    }

    public void addParkingFloor(int floorNumber) {
        ParkingFloor floor = new ParkingFloor();
        floors.put(floorNumber, floor);
    }

    public void display() {
        for (Map.Entry<Integer, ParkingFloor> m : floors.entrySet()) {
            System.out.println(m.getValue().generateLiveMap());
        }
    }
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        VehicleType type = vehicle.getType();
        for (ParkingFloor floor : floors.values()) {
            for (ParkingSlot slot : floor.getSlots().values()) {
                if (!slot.isOccupied() && slot.getAllowedVehicleType() == type) {
                    slot.occupy(vehicle);
                    return new ParkingTicket(floor.getFloorNumber(), slot.getSlotNumber(), type);
                }
            }
        }
        addToWaitingQueue(vehicle);
        return null;
    }

    public boolean unparkVehicle(ParkingTicket ticket) {
        ParkingFloor floor = floors.get(ticket.getFloorNumber());
        if (floor != null) {
            ParkingSlot slot = floor.getSlots().get(ticket.getSlotNumber());
            if (slot != null && slot.isOccupied() && slot.getAllowedVehicleType() == ticket.getVehicleType()) {
                slot.vacate();
                if (hasWaitingVehicles(ticket.getVehicleType())) {
                    Vehicle waitingVehicle = removeFromWaitingQueue(ticket.getVehicleType()) ;
                    parkVehicle(waitingVehicle);
                }
                return true;
            }
        }
        return false; // Invalid ticket or slot not found
    }

    public void displayFreeSlots(int floorNumber, VehicleType vehicleType) {
        ParkingFloor floor = floors.get(floorNumber);
        if (floor != null) {
            int freeSlots = (int) floor.getSlots().values().stream().filter(slot -> !slot.isOccupied() && slot.getAllowedVehicleType() == vehicleType).count();
            System.out.println("Free Slots on Floor " + floorNumber + " for " + vehicleType + ": " + freeSlots);
        }
    }

    public void displayAllFreeSlots(int floorNumber, VehicleType vehicleType) {
        ParkingFloor floor = floors.get(floorNumber);
        if (floor != null) {
            System.out.println("All Free Slots on Floor " + floorNumber + " for " + vehicleType + ":");
            floor.getSlots().values().stream().filter(slot -> !slot.isOccupied() && slot.getAllowedVehicleType() == vehicleType).forEach(slot -> System.out.println("Slot " + slot.getSlotNumber()));
        }
    }

    public void displayAllOccupiedSlots(int floorNumber, VehicleType vehicleType) {
        ParkingFloor floor = floors.get(floorNumber);
        if (floor != null) {
            System.out.println("All Occupied Slots on Floor " + floorNumber + " for " + vehicleType + ":");
            floor.getSlots().values().stream().filter(slot -> slot.isOccupied() && slot.getAllowedVehicleType() == vehicleType).forEach(slot -> System.out.println("Slot " + slot.getSlotNumber()));
        }
    }

    public void addToWaitingQueue(Vehicle vehicle) {
        if(waitingQueue.get(vehicle.getType()).size()<=ParkingFloor.floorNumber)
            waitingQueue.get(vehicle.getType()).add(vehicle);
    }

    public Vehicle removeFromWaitingQueue(VehicleType vehicleType) {
        return waitingQueue.get(vehicleType).remove();
    }

    public boolean hasWaitingVehicles(VehicleType vehicleType) {
        return !waitingQueue.get(vehicleType).isEmpty();
    }
}

