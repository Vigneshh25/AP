package ParkingLot.Parking;

import java.util.Scanner;

/**
 * Created by Vignesh.V on 24/05/24.
 */
public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addParkingFloor(1);
        parkingLot.addParkingFloor(2);

        CarRegistrationModule carRegistrationModule = new CarRegistrationModule();
        carRegistrationModule.registerCar("ABC123", "Red", VehicleType.CAR);
        carRegistrationModule.registerCar("qwe12a", "Red", VehicleType.CAR);
        carRegistrationModule.registerCar("XYZ456", "Blue", VehicleType.BIKE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            parkingLot.display();

            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Display Free Slots");
            System.out.println("4. Display All Free Slots");
            System.out.println("5. Display All Occupied Slots");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character


            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle registration number: ");
                    String regNumber = scanner.nextLine();
                    Vehicle vehicle = carRegistrationModule.getCar(regNumber);

                    if (vehicle != null) {
                        ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
                        if (ticket != null) {
                            System.out.println("Vehicle parked successfully. Ticket details: " + ticket);
                        } else {
                            System.out.println("No available slots for the vehicle type.");
                        }
                    } else {
                        System.out.println("Vehicle not registered.");
                    }
                    break;

                case 2:
                    System.out.print("Enter ticket details (floorNumber-slotNumber-vehicleType): ");
                    String[] ticketDetails = scanner.nextLine().split("-");
                    int floorNumber = Integer.parseInt(ticketDetails[0]);
                    int slotNumber = Integer.parseInt(ticketDetails[1]);
                    VehicleType vehicleType = VehicleType.valueOf(ticketDetails[2]);

                    ParkingTicket unparkTicket = new ParkingTicket(floorNumber, slotNumber, vehicleType);
                    boolean success = parkingLot.unparkVehicle(unparkTicket);
                    if (success) {
                        System.out.println("Vehicle unparked successfully.");
                    } else {
                        System.out.println("Invalid ticket or slot not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter floor number: ");
                    int displayFloorNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter vehicle type (CAR, BIKE, TRUCK): ");
                    VehicleType displayVehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                    parkingLot.displayFreeSlots(displayFloorNumber, displayVehicleType);
                    break;

                case 4:
                    System.out.print("Enter floor number: ");
                    int displayAllFloorNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter vehicle type (CAR, BIKE, TRUCK): ");
                    VehicleType displayAllVehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                    parkingLot.displayAllFreeSlots(displayAllFloorNumber, displayAllVehicleType);
                    break;

                case 5:
                    System.out.print("Enter floor number: ");
                    int displayOccupiedFloorNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter vehicle type (CAR, BIKE, TRUCK): ");
                    VehicleType displayOccupiedVehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                    parkingLot.displayAllOccupiedSlots(displayOccupiedFloorNumber, displayOccupiedVehicleType);
                    break;

                case 6:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
    }
}
