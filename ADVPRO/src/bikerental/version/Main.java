package bikerental.version;

import java.time.LocalDate;
import java.util.*;

// Main class demonstrating usage
public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // Add vehicles
        Vehicle car1 = VehicleFactory.createVehicle("car", "BC123", "ABC123", "Toyota", "Camry", 2020, "A1");
        Vehicle car2 = VehicleFactory.createVehicle("car", "BC124", "DEF456", "Honda", "Accord", 2019, "B1");
        inventory.addVehicle(car1);
        inventory.addVehicle(car2);

        // Add members
        Member member1 = new Member("M001", "Alice", "alice@example.com");
        Member member2 = new Member("M002", "Bob", "bob@example.com");

        // Create reservations
        Reservation reservation1 = new Reservation("R001", member1, car1, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 10));
        reservation1.addInsurance(new Insurance(reservation1, 20));
        reservation1.addEquipment(new Equipment(reservation1, 15));
        reservation1.addService(new Service(reservation1, 10));

        Reservation reservation2 = new Reservation("R002", member2, car2, LocalDate.of(2023, 6, 5), LocalDate.of(2023, 6, 12));
        reservation2.addInsurance(new Insurance(reservation2, 25));
        reservation2.addEquipment(new Equipment(reservation2, 18));
        reservation2.addService(new Service(reservation2, 12));

        // Add reservations to members
        member1.addReservation(reservation1);
        member2.addReservation(reservation2);

        // Notifications
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new MemberNotificationService(member1));
        notificationService.addObserver(new MemberNotificationService(member2));

        notificationService.notifyObservers("Your reservation is approaching the pick-up date.");
        notificationService.notifyObservers("Your vehicle is nearing the due date.");

        // Rentals and returns
        Rental rental1 = new Rental("RT001", reservation1, LocalDate.of(2023, 6, 10));
        rental1.returnVehicle(LocalDate.of(2023, 6, 12), new DefaultLateFeeStrategy());

        VehicleLog.getInstance().log("Vehicle BC123 rented by Alice.");
        VehicleLog.getInstance().log("Vehicle BC124 rented by Bob.");

        // Output logs
        System.out.println("Vehicle Logs:");
        for (String log : VehicleLog.getInstance().getLogs()) {
            System.out.println(log);
        }

        // Search vehicles
        List<Vehicle> searchedVehicles = inventory.searchVehicles("Toyota", "Camry");
        System.out.println("Searched Vehicles:");
        for (Vehicle vehicle : searchedVehicles) {
            System.out.println(vehicle.getBarcode());
        }
    }
}
