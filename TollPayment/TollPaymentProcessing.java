import java.util.ArrayList;

public class TollPaymentProcessing {
    public static void main(String[] args) {
        TollGate system = new TollGate();
        system.addToll("Toll 1");
        system.addToll("Toll 2");
        system.addToll("Toll 3");
        system.addChargingScheme("Toll 1", "Car", 100);
        system.addChargingScheme("Toll 2", "Car", 150);
        system.addChargingScheme("Toll 3", "Car", 200);

        system.addVehicle("Car", "KA-01-HH-1234", true);
        system.addVehicle("Car", "KA-01-HH-5678", false);

        ArrayList<String> tollsPassed = new ArrayList<String>();
        tollsPassed.add("Toll 1");
        tollsPassed.add("Toll 2");
        tollsPassed.add("Toll 3");
        system.addJourney("KA-01-HH-1234", "Start", "Destination", tollsPassed);

        tollsPassed = new ArrayList<String>();
        tollsPassed.add("Toll 3");
        tollsPassed.add("Toll 2");
        tollsPassed.add("Toll 1");
        system.addJourney("KA-01-HH-5678", "Start", "Destination", tollsPassed);

        System.out.println("Total Toll Paid by KA-01-HH-1234: " + system.getTotalTollPaid("KA-01-HH-1234"));

        System.out.println("\nToll Details: ");
        system.displayTollDetails("Toll 1");

        System.out.println("\nVehicle Details: ");
        system.displayVehicleDetails("KA-01-HH-1234");

        ArrayList<String> allTolls = new ArrayList<String>();
        allTolls.add("Toll 1");
        allTolls.add("Toll 2");
        allTolls.add("Toll 3");
        System.out.println("\nShortest Route and Amount Paid: ");
        system.findShortestRouteAndAmount("Toll 1", "Toll 3", allTolls);
    }
}



// package TollPayment;

// import java.util.ArrayList;
// import java.util.Scanner;

// public class TollPaymentProcessing {
//     public static void main(String[] args) {
//         TollGate system = new TollGate();
//         system.addToll("Toll 1");
//         system.addToll("Toll 2");
//         system.addToll("Toll 3");
//         system.addChargingScheme("Toll 1", "Car", 100);
//         system.addChargingScheme("Toll 2", "Car", 150);
//         system.addChargingScheme("Toll 3", "Car", 200);

//         system.addVehicle("Car", "KA-01-HH-1234", true);
//         system.addVehicle("Car", "KA-01-HH-5678", false);

//         ArrayList<String> tollsPassed = new ArrayList<String>();
//         tollsPassed.add("Toll 1");
//         tollsPassed.add("Toll 2");
//         tollsPassed.add("Toll 3");
//         system.addJourney("KA-01-HH-1234", "Start", "Destination", tollsPassed);

//         tollsPassed = new ArrayList<String>();
//         tollsPassed.add("Toll 3");
//         tollsPassed.add("Toll 2");
//         tollsPassed.add("Toll 1");
//         system.addJourney("KA-01-HH-5678", "Start", "Destination", tollsPassed);

//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.println("\nEnter a command (1-7):\n1. Get total toll paid by a vehicle\n2. Display toll details\n3. Display vehicle details\n4. Find shortest route and amount paid\n5. Add toll\n6. Add charging scheme\n7. Exit");
//             int command = scanner.nextInt();
//             scanner.nextLine(); // consume the newline character

//             switch (command) {
//                 case 1:
//                     System.out.println("\nEnter vehicle number:");
//                     String vehicleNumber = scanner.nextLine();
//                     System.out.println("Total Toll Paid by " + vehicleNumber + ": " + system.getTotalTollPaid(vehicleNumber));
//                     break;
//                 case 2:
//                     System.out.println("\nEnter toll name:");
//                     String tollName = scanner.nextLine();
//                     system.displayTollDetails(tollName);
//                     break;
//                 case 3:
//                     System.out.println("\nEnter vehicle number:");
//                     vehicleNumber = scanner.nextLine();
//                     system.displayVehicleDetails(vehicleNumber);
//                     break;
//                 case 4:
//                     System.out.println("\nEnter starting toll name:");
//                     String startingTollName = scanner.nextLine();
//                     System.out.println("Enter destination toll name:");
//                     String destinationTollName = scanner.nextLine();
//                     ArrayList<String> allTolls = new ArrayList<String>();
//                     allTolls.add("Toll 1");
//                     allTolls.add("Toll 2");
//                     allTolls.add("Toll 3");
//                     system.findShortestRouteAndAmount(startingTollName, destinationTollName, allTolls);
//                     break;
//                 case 5:
//                     System.out.println("\nEnter toll name:");
//                     tollName = scanner.nextLine();
//                     system.addToll(tollName);
//                     break;
//                 case 6:
//                     System.out.println("\nEnter toll name:");
//                     tollName = scanner.nextLine();
//                     System.out.println("Enter vehicle type:");
//                     String vehicleType = scanner.nextLine();
//                     System.out.println("Enter toll amount:");
//                     int tollAmount = scanner.nextInt();
//                     scanner.nextLine(); // consume the newline character
//                     system.addChargingScheme(tollName, vehicleType, tollAmount);

//             }}}}
