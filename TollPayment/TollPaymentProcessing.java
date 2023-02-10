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
