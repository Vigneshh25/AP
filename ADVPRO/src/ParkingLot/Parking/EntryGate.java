package ParkingLot.Parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class EntryGate {
    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket enterVehicle(String registrationNumber, CarRegistrationModule carRegistrationModule) {
        Vehicle vehicle = carRegistrationModule.getCar(registrationNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not registered.");
            return null;
        }

        ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
        if (ticket != null) {
            ticket.setEntryTime(LocalDateTime.now());
            System.out.println("Vehicle parked successfully. Ticket details: " + ticket);
        } else {
            System.out.println("No available slots for the vehicle type.");
        }
        return ticket;
    }
}

class ExitGate {
    private final ParkingLot parkingLot;
    private final CostCalculationModule costCalculationModule;

    public ExitGate(ParkingLot parkingLot, CostCalculationModule costCalculationModule) {
        this.parkingLot = parkingLot;
        this.costCalculationModule = costCalculationModule;
    }

    public void exitVehicle(String registrationNumber) {
        ParkingTicket ticket = parkingLot.getTicket(registrationNumber);
        if (ticket == null) {
            System.out.println("Invalid ticket: ticket not found.");
            return;
        }
        if (ticket.getEntryTime() == null) {
            System.out.println("Invalid ticket: entry time not found.");
            return;
        }

        boolean success = parkingLot.unparkVehicle(ticket);
        if (success) {
            LocalDateTime exitTime = LocalDateTime.now();
            long duration = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
            double fee = costCalculationModule.calculateParkingFee((int) duration, ticket.getVehicleType());
            System.out.println("Vehicle unparked successfully. Parking fee: $" + fee);
        } else {
            System.out.println("Invalid ticket or slot not found.");
        }
    }
}
