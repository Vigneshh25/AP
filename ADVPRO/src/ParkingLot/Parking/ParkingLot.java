import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK
}

class Vehicle {
    private final String registrationNumber;
    private final String color;
    private final VehicleType type;

    public Vehicle(String registrationNumber, String color, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getType() {
        return type;
    }
}

class ParkingSlot {
    private final int slotNumber;
    private final VehicleType allowedVehicleType;
    private boolean occupied;

    private Vehicle vehicle;

    public ParkingSlot(int slotNumber, VehicleType allowedVehicleType) {
        this.slotNumber = slotNumber;
        this.allowedVehicleType = allowedVehicleType;
        this.occupied = false;
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getAllowedVehicleType() {
        return allowedVehicleType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy(Vehicle vehicle) {
        occupied = true;
        this.vehicle =vehicle;
    }

    public void vacate() {
        occupied = false;
        this.vehicle = null;
    }

}

class ParkingFloor {

    public static int floorNumber;

    private int floor;
    private  Map<Integer, ParkingSlot> slots;

    public ParkingFloor() {
        floorNumber++;
        this.floor = floorNumber;
        Map<Integer, ParkingSlot> slotMap = new TreeMap<>();
        for (int i = 1; i <= 5; i++) {
            if (i <= 2) slotMap.put(i, new ParkingSlot(i, VehicleType.BIKE));
            else if (i <= 4) slotMap.put(i, new ParkingSlot(i, VehicleType.CAR));
            else slotMap.put(i, new ParkingSlot(i, VehicleType.TRUCK));
        }
        this.slots = slotMap;
    }

    public String generateLiveMap() {
        StringBuilder liveMap = new StringBuilder();
        liveMap.append("Live Parking Map - Floor ").append(floorNumber).append("\n");

        int maxSlotNumber = slots.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);

        for (int i = 1; i <= maxSlotNumber; i++) {
            ParkingSlot slot = slots.getOrDefault(i, new ParkingSlot(i, VehicleType.CAR));
            liveMap.append("|");

            if (slot.isOccupied()) {
                liveMap.append(slot.getVehicle().getRegistrationNumber());
            } else {

                liveMap.append(" ");
            }

            liveMap.append(" ");
        }

        return liveMap.toString();
    }

    public void addParkingSlot(int slotNumber, VehicleType allowedVehicleType) {
        ParkingSlot slot = new ParkingSlot(slotNumber, allowedVehicleType);
        slots.put(slotNumber, slot);
    }

    public Map<Integer, ParkingSlot> getSlots() {
        return slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingSlot getParkingSlotByNumber(int slotNumber) {
        if (slotNumber >= 1 && slotNumber <= slots.size()) {
            return slots.get(slotNumber);
        }
        return null;
    }

    public ParkingSlot getNextAvailableSlot() {
        for (ParkingSlot slot : slots.values()) {
            if (!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }
}

class ParkingTicket {
    private final int floorNumber;
    private final int slotNumber;
    private final VehicleType vehicleType;

    public ParkingTicket(int floorNumber, int slotNumber, VehicleType vehicleType) {
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

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

class CarRegistrationModule {
    private final Map<String, Vehicle> registeredCars;

    public CarRegistrationModule() {
        this.registeredCars = new HashMap<>();
    }

    public void registerCar(String registrationNumber, String color, VehicleType type) {
        Vehicle car = new Vehicle(registrationNumber, color, type);
        registeredCars.put(registrationNumber, car);
    }

    public Vehicle getCar(String registrationNumber) {
        return registeredCars.get(registrationNumber);
    }
}

class UserRegistrationModule {
    private final Map<String, String> userContacts;
    private final Map<String, String> userPreferences;

    public UserRegistrationModule() {
        this.userContacts = new HashMap<>();
        this.userPreferences = new HashMap<>();
    }

    public void registerUser(String username, String contactDetails, String preferences) {
        userContacts.put(username, contactDetails);
        userPreferences.put(username, preferences);
    }

    public String getContactDetails(String username) {
        return userContacts.get(username);
    }

    public String getPreferences(String username) {
        return userPreferences.get(username);
    }
}

class CostCalculationModule {
    public double calculateParkingFee(int duration, VehicleType vehicleType) {
        // Simplified fee calculation based on duration and vehicle type
        double baseFee = 5.0;
        double rateMultiplier = (vehicleType == VehicleType.TRUCK) ? 1.5 : 1.0;
        return baseFee * duration * rateMultiplier;
    }
}

class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addParkingFloor(1);
        parkingLot.addParkingFloor(2);

        CarRegistrationModule carRegistrationModule = new CarRegistrationModule();
        carRegistrationModule.registerCar("ABC123", "Red", VehicleType.CAR);
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
