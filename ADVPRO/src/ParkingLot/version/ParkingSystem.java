package ParkingLot.version;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Parking Lot
class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> floors;
    private Map<String, EntryPanel> entryPanels;
    private Map<String, ExitPanel> exitPanels;

    private ParkingLot() {
        floors = new ArrayList<>();
        entryPanels = new ConcurrentHashMap<>();
        exitPanels = new ConcurrentHashMap<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void addEntryPanel(EntryPanel panel) {
        entryPanels.put(panel.getId(), panel);
    }

    public void addExitPanel(ExitPanel panel) {
        exitPanels.put(panel.getId(), panel);
    }

    public EntryPanel getEntryPanel(String id) {
        return entryPanels.get(id);
    }

    public ExitPanel getExitPanel(String id) {
        return exitPanels.get(id);
    }

    public ParkingSpot findFreeSpot(VehicleType type) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findFreeSpot(type);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }
}


// Parking Floor
class ParkingFloor {
    private String id;
    private Map<ParkingSpotType, List<ParkingSpot>> spots;

    public ParkingFloor(String id) {
        this.id = id;
        spots = new HashMap<>();
        for (ParkingSpotType type : ParkingSpotType.values()) {
            spots.put(type, new ArrayList<>());
        }
    }

    public void addParkingSpot(ParkingSpot spot) {
        spots.get(spot.getType()).add(spot);
    }

    public ParkingSpot findFreeSpot(VehicleType type) {
        ParkingSpotType spotType = getSpotType(type);
        for (ParkingSpot spot : spots.get(spotType)) {
            if (spot.isFree()) {
                return spot;
            }
        }
        return null;
    }

    public void showAvailableSpots() {
        for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : spots.entrySet()) {
            System.out.println(entry.getKey() + " spots:");
            for (ParkingSpot spot : entry.getValue()) {
                if (spot.isFree()) {
                    System.out.println("- Spot " + spot.SpotNumber() + " is available.");
                } else {
                    System.out.println("- Spot " + spot.SpotNumber() + " is occupied by " + spot.getParkedVehicle().getType());
                }
            }
        }
    }

    private ParkingSpotType getSpotType(VehicleType type) {
        switch (type) {
            case CAR:
                return ParkingSpotType.COMPACT;
            case MOTORCYCLE:
                return ParkingSpotType.MOTORCYCLE;
            case ELECTRIC:
                return ParkingSpotType.ELECTRIC;
            case TRUCK:
            case VAN:
                return ParkingSpotType.LARGE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
    public void printFloorMap() {
        System.out.println("Live Parking Map - " + id);

        // Determine the maximum number of spots in any row to align the grid
        int maxSlots = spots.values().stream().mapToInt(List::size).max().orElse(0);

        // Iterate through each row (each ParkingSpotType)
        for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : spots.entrySet()) {
            System.out.print("|");

            // Print each spot in the current row
            for (ParkingSpot spot : entry.getValue()) {
                if (spot.isFree()) {
                    System.out.print(" [ ] ");
                } else {
                    String vehicleInfo = spot.getParkedVehicle().getType().toString().substring(0, 3) + "-" + spot.getParkedVehicle().getLicensePlate().substring(spot.getParkedVehicle().getLicensePlate().length() - 3);
                    System.out.print(" [" + vehicleInfo + "] ");
                }
            }

            // Fill with empty spots if there are fewer spots than maxSlots
            for (int i = entry.getValue().size(); i < maxSlots; i++) {
                System.out.print(" [ ] ");
            }

            System.out.println("|");
        }

        System.out.println();
    }
}

// ParkingSpot class
abstract class ParkingSpot {
    private String number;
    private ParkingSpotType type;
    private boolean free;
    private Vehicle currentVehicle;
    private Lock lock;

    public ParkingSpot(String number, ParkingSpotType type) {
        this.type = type;
        this.number = number;
        this.free = true;
        this.lock = new ReentrantLock();
    }

    public boolean isFree() {
        return free;
    }

    public ParkingSpotType getType() {
        return type;
    }

    public void park(Vehicle vehicle) {
        lock.lock();
        try {
            currentVehicle = vehicle;
            free = false;
        } finally {
            lock.unlock();
        }
    }

    public void freeUp() {
        lock.lock();
        try {
            currentVehicle = null;
            free = true;
        } finally {
            lock.unlock();
        }
    }

    public String SpotNumber() {
        return number;
    }

    public Vehicle getParkedVehicle() {
        return currentVehicle;
    }

    public abstract boolean canFitVehicle(VehicleType vehicleType);
}

class CompactSpot extends ParkingSpot {
    public CompactSpot(String id) {
        super(id, ParkingSpotType.COMPACT);
    }

    @Override
    public boolean canFitVehicle(VehicleType vehicleType) {
        return vehicleType == VehicleType.CAR || vehicleType == VehicleType.MOTORCYCLE;
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot(String id) {
        super(id, ParkingSpotType.LARGE);
    }

    @Override
    public boolean canFitVehicle(VehicleType vehicleType) {
        return vehicleType == VehicleType.TRUCK || vehicleType == VehicleType.VAN;
    }
}

// Additional spot types: HandicappedSpot, MotorcycleSpot, ElectricSpot...

// Vehicles
abstract class Vehicle {
    private String licensePlate;
    private VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}

class ElectricCar extends Vehicle {
    public ElectricCar(String licensePlate) {
        super(licensePlate, VehicleType.ELECTRIC);
    }
}

// Additional vehicle types: Motorcycle, ElectricCar...

// Ticket class
class Ticket {
    private String ticketNumber;
    private long startTime;
    private long endTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(String ticketNumber, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.startTime = System.currentTimeMillis();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void endParking() {
        this.endTime = System.currentTimeMillis();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}

// Payment strategy pattern
interface PaymentStrategy {
    boolean pay(Ticket ticket, double amount);
}

class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(Ticket ticket, double amount) {
        System.out.println("Payment of $" + amount + " received in cash for ticket: " + ticket.getParkingSpot());
        // Cash payment processing logic...
        return true;
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean pay(Ticket ticket, double amount) {
        System.out.println("Payment of $" + amount + " received via credit card for ticket: " + ticket.getParkingSpot());
        return true;
    }
}

// DisplayBoard class using Observer pattern
class DisplayBoard {
    private Map<ParkingSpotType, Integer> availableSpots;

    public DisplayBoard() {
        availableSpots = new ConcurrentHashMap<>();
    }

    public void update(ParkingSpotType type, int count) {
        availableSpots.put(type, count);
    }

    public void showAvailableSpots() {
        for (Map.Entry<ParkingSpotType, Integer> entry : availableSpots.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " spots available");
        }
    }
}

// Panels
class EntryPanel {
    private String id;

    public EntryPanel(String id) {
        this.id = id;
    }

    public Ticket issueTicket(Vehicle vehicle) {
        ParkingSpot spot = ParkingLot.getInstance().findFreeSpot(vehicle.getType());
        if (spot != null) {
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, spot);
            spot.park(vehicle);
            System.out.println("Ticket issued: " + ticket.getTicketNumber() + " for vehicle: " + vehicle.getLicensePlate());
            return ticket;
        }
        System.out.println("Parking Full for vehicle: " + vehicle.getLicensePlate());
        return null;
    }

    public String getId() {
        return id;
    }
}

class ExitPanel {
    private String id;

    public ExitPanel(String id) {
        this.id = id;
    }

    public boolean processPayment(Ticket ticket, PaymentStrategy paymentStrategy, double amount) {
        if (paymentStrategy.pay(ticket, amount)) {
            ticket.getParkingSpot().freeUp();
            ticket.endParking();
            System.out.println("Payment processed for ticket: " + ticket.getTicketNumber());
            return true;
        }
        System.out.println("Payment failed for ticket: " + ticket.getTicketNumber());
        return false;
    }

    public String getId() {
        return id;
    }
}

// CustomerInfoPortal class
class CustomerInfoPortal {
    public boolean payForParking(Ticket ticket, PaymentStrategy paymentStrategy, double amount) {
        return paymentStrategy.pay(ticket, amount);
    }
}

// Enums
enum VehicleType {
    CAR, TRUCK, VAN, MOTORCYCLE, ELECTRIC
}

enum ParkingSpotType {
    COMPACT, LARGE, HANDICAPPED, MOTORCYCLE, ELECTRIC
}

// WebCrawlerWithSameHostnameMain method for testing
public class ParkingSystem {
    public static void main(String[] args) {
        // Setup ParkingLot
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingFloor floor1 = new ParkingFloor("Floor1");
        ParkingFloor floor2 = new ParkingFloor("Floor2");

        ParkingSpot spot1 = new CompactSpot("1A");
        ParkingSpot spot2 = new LargeSpot("1B");
        ParkingSpot spot3 = new CompactSpot("1C");

        // Add parking spots to floors
        for (int i = 0; i < 3; i++) {
            floor1.addParkingSpot(spot1);
            floor1.addParkingSpot(spot2);
            floor1.addParkingSpot(spot3);
        }

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        // Setup Entry and Exit Panels
        EntryPanel entryPanel1 = new EntryPanel("Entry1");
        EntryPanel entryPanel2 = new EntryPanel("Entry2");
        ExitPanel exitPanel1 = new ExitPanel("Exit1");
        ExitPanel exitPanel2 = new ExitPanel("Exit2");

        parkingLot.addEntryPanel(entryPanel1);
        parkingLot.addEntryPanel(entryPanel2);
        parkingLot.addExitPanel(exitPanel1);
        parkingLot.addExitPanel(exitPanel2);

        // Simulate entry and exit of vehicles
        System.out.println("---- Simulating Entry and Exit ----");

        // Vehicle entry
        Vehicle car1 = new Car("CAR123");
        Ticket ticket1 = entryPanel1.issueTicket(car1);
        System.out.println("Car1 Ticket: " + ticket1);

        Vehicle truck1 = new Truck("TRUCK123");
        Ticket ticket2 = entryPanel1.issueTicket(truck1);
        System.out.println("Truck1 Ticket: " + ticket2);

        Vehicle car2 = new Car("CAR456");
        Ticket ticket3 = entryPanel2.issueTicket(car2);
        System.out.println("Car2 Ticket: " + ticket3);

        // Check for available spots
        System.out.println("---- Display Available Spots ----");
        floor1.showAvailableSpots();
        floor2.showAvailableSpots();

        // Print floor map
        System.out.println("---- Floor Maps ----");
        floor1.printFloorMap();
        floor2.printFloorMap();

        // Vehicle exit
        System.out.println("---- Processing Payments ----");
        boolean paymentSuccess1 = exitPanel1.processPayment(ticket1, new CashPayment(), 10.0);
        System.out.println("Payment for Car1: " + paymentSuccess1);

        boolean paymentSuccess2 = exitPanel1.processPayment(ticket2, new CreditCardPayment(), 20.0);
        System.out.println("Payment for Truck1: " + paymentSuccess2);

        boolean paymentSuccess3 = exitPanel2.processPayment(ticket3, new CashPayment(), 15.0);
        System.out.println("Payment for Car2: " + paymentSuccess3);

        // Check for available spots after exit
        System.out.println("---- Display Available Spots After Exit ----");
        floor1.showAvailableSpots();
        floor2.showAvailableSpots();

        // Edge Case: Parking Lot Full
        System.out.println("---- Simulating Parking Lot Full ----");
        for (int i = 0; i < 20; i++) {
            Vehicle vehicle = new Car("CAR" + i);
            Ticket ticket = entryPanel1.issueTicket(vehicle);
            if (ticket == null) {
                System.out.println("Parking Full! Could not issue ticket for CAR" + i);
                break;
            }
        }

        // Edge Case: Payment at Customer Info Portal
        System.out.println("---- Simulating Payment at Customer Info Portal ----");
        Vehicle car3 = new Car("CAR789");
        Ticket ticket4 = entryPanel1.issueTicket(car3);
        System.out.println("Car3 Ticket: " + ticket4);

        CustomerInfoPortal infoPortal = new CustomerInfoPortal();
        boolean portalPaymentSuccess = infoPortal.payForParking(ticket4, new CreditCardPayment(), 10.0);
        System.out.println("Payment at Info Portal for Car3: " + portalPaymentSuccess);

        boolean exitPaymentSuccess = exitPanel2.processPayment(ticket4, new CashPayment(), 0.0); // Already paid
        System.out.println("Exit Payment for Car3 (should be zero): " + exitPaymentSuccess);
    }
}

