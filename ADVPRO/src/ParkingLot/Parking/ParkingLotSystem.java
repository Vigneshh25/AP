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
        carRegistrationModule.registerCar("QWE12A", "Red", VehicleType.CAR);
        carRegistrationModule.registerCar("XYZ456", "Blue", VehicleType.BIKE);

        CostCalculationModule costCalculationModule = new CostCalculationModule();
        EntryGate entryGate = new EntryGate(parkingLot);
        ExitGate exitGate = new ExitGate(parkingLot, costCalculationModule);

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
                    entryGate.enterVehicle(regNumber, carRegistrationModule);
                    break;

                case 2:
                    System.out.print("Enter vehicle registration number: ");
                    String unparkRegNumber = scanner.nextLine();
                    exitGate.exitVehicle(unparkRegNumber);
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
