import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class ParkingLot {
    private int capacity;
    private TreeMap<Integer, String> slots;  // TreeMap for sorted slots
    private Map<String, Integer> registrationToSlot;
    private Map<String, String> registrationToColor;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new TreeMap<>();
        this.registrationToSlot = new HashMap<>();
        this.registrationToColor = new HashMap<>();
    }

    public void park(String registrationNumber, String color) {
        if (slots.size() < capacity) {
            int slotNumber = slots.size() + 1;
            slots.put(slotNumber, registrationNumber);
            registrationToSlot.put(registrationNumber, slotNumber);
            registrationToColor.put(registrationNumber, color);
            System.out.println("Allocated slot number: " + slotNumber);
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    public void leave(int slotNumber) {
        if (slots.containsKey(slotNumber)) {
            String registrationNumber = slots.remove(slotNumber);
            registrationToSlot.remove(registrationNumber);
            registrationToColor.remove(registrationNumber);
            System.out.println("Slot number " + slotNumber + " is free");
        } else {
            System.out.println("Invalid slot number");
        }
    }

    public void status() {
        System.out.println("Slot No. Registration No Color");
        for (Map.Entry<Integer, String> entry : slots.entrySet()) {
            int slotNumber = entry.getKey();
            String registrationNumber = entry.getValue();
            String color = registrationToColor.get(registrationNumber);
            System.out.println(slotNumber + " " + registrationNumber + " " + color);
        }
    }

    public void getRegistrationNumbersForColor(String color) {
        boolean found = false;
        for (Map.Entry<String, String> entry : registrationToColor.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(color)) {
                if (found) {
                    System.out.print(", ");
                }
                System.out.print(entry.getKey());
                found = true;
            }
        }
        System.out.println(found ? "" : "Not found");
    }

    public void getSlotNumbersForColor(String color) {
        boolean found = false;
        for (Map.Entry<String, String> entry : registrationToColor.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(color)) {
                if (found) {
                    System.out.print(", ");
                }
                System.out.print(registrationToSlot.get(entry.getKey()));
                found = true;
            }
        }
        System.out.println(found ? "" : "Not found");
    }

    public void getSlotNumberForRegistration(String registrationNumber) {
        Integer slotNumber = registrationToSlot.get(registrationNumber);
        System.out.println(slotNumber != null ? slotNumber : "Not found");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");

            switch (tokens[0]) {
                case "create_parking_lot":
                    int capacity = Integer.parseInt(tokens[1]);
                    parkingLot = new ParkingLot(capacity);
                    System.out.println("Created a parking lot with " + capacity + " slots");
                    break;

                case "park":
                    parkingLot.park(tokens[1], tokens[2]);
                    break;

                case "leave":
                    parkingLot.leave(Integer.parseInt(tokens[1]));
                    break;

                case "status":
                    parkingLot.status();
                    break;

                case "registration_numbers_for_cars_with_colour":
                    parkingLot.getRegistrationNumbersForColor(tokens[1]);
                    break;

                case "slot_numbers_for_cars_with_colour":
                    parkingLot.getSlotNumbersForColor(tokens[1]);
                    break;

                case "slot_number_for_registration_number":
                    parkingLot.getSlotNumberForRegistration(tokens[1]);
                    break;

                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
