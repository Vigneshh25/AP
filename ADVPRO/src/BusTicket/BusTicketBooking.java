package BusTicket;

import java.util.*;
import java.util.stream.Collectors;

interface Bus {
    void viewSeats();

    boolean isSeatAvailable(int seatNumber);

    int[] bookTickets(int numberOfTickets);

    void cancelTickets(int[] bookedTickets);

    double calculateFare(int numberOfTickets);

    int[] getBookedSeats();

    int getTotalSeats();

    String getBusType();

    String getSeatType();
}


// Concrete implementations of the Bus interface
class AcSeaterBus implements Bus {
    private final int totalSeats = 40;
    private final Set<Integer> bookedSeats = new HashSet<>();
    private final double farePerSeat = 1000.00;

    @Override
    public void viewSeats() {
        System.out.println("AC Seater Bus - Available Seats:");
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i)) {
                System.out.print(i + " ");
            } else {
                System.out.print("X ");
            }

            if (i % 4 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean isSeatAvailable(int seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    @Override
    public int[] bookTickets(int numberOfTickets) {
        int[] bookedSeatsArray = new int[numberOfTickets];
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i) && numberOfTickets > 0) {
                bookedSeats.add(i);
                bookedSeatsArray[numberOfTickets - 1] = i;
                numberOfTickets--;
            }
        }
        return bookedSeatsArray;
    }

    @Override
    public void cancelTickets(int[] bookedTickets) {
        for (int seatNumber : bookedTickets) {
            bookedSeats.remove(seatNumber);
        }
    }

    @Override
    public double calculateFare(int numberOfTickets) {
        return farePerSeat * numberOfTickets;
    }

    @Override
    public int[] getBookedSeats() {
        return bookedSeats.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public int getTotalSeats() {
        return totalSeats;
    }

    public String getBusType() {
        return "AC";
    }

    public String getSeatType() {
        return "seater";
    }

}


class AcSleeperBus implements Bus {
    private final int totalSeats = 20;
    private final Set<Integer> bookedSeats = new HashSet<>();
    private final double farePerSeat = 1200.00;

    @Override
    public void viewSeats() {
        System.out.println("AC Sleeper Bus - Available Seats:");
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i)) {
                System.out.print(i + " ");
            } else {
                System.out.print("X ");
            }

            if (i % 4 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean isSeatAvailable(int seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    @Override
    public int[] bookTickets(int numberOfTickets) {
        int[] bookedSeatsArray = new int[numberOfTickets];
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i) && numberOfTickets > 0) {
                bookedSeats.add(i);
                bookedSeatsArray[numberOfTickets - 1] = i;
                numberOfTickets--;
            }
        }
        return bookedSeatsArray;
    }

    @Override
    public void cancelTickets(int[] bookedTickets) {
        for (int seatNumber : bookedTickets) {
            bookedSeats.remove(seatNumber);
        }
    }

    @Override
    public double calculateFare(int numberOfTickets) {
        return farePerSeat * numberOfTickets;
    }

    public String getBusType() {
        return "AC";
    }

    public String getSeatType() {
        return "sleeper";
    }

    @Override
    public int[] getBookedSeats() {
        return bookedSeats.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public int getTotalSeats() {
        return totalSeats;
    }
}

class NonAcSeaterBus implements Bus {
    private final int totalSeats = 50;
    private final Set<Integer> bookedSeats = new HashSet<>();
    private final double farePerSeat = 800.00;

    @Override
    public void viewSeats() {
        System.out.println("Non-AC Seater Bus - Available Seats:");
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i)) {
                System.out.print(i + " ");
            } else {
                System.out.print("X ");
            }

            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean isSeatAvailable(int seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    @Override
    public int[] bookTickets(int numberOfTickets) {
        int[] bookedSeatsArray = new int[numberOfTickets];
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i) && numberOfTickets > 0) {
                bookedSeats.add(i);
                bookedSeatsArray[numberOfTickets - 1] = i;
                numberOfTickets--;
            }
        }
        return bookedSeatsArray;
    }

    @Override
    public void cancelTickets(int[] bookedTickets) {
        for (int seatNumber : bookedTickets) {
            bookedSeats.remove(seatNumber);
        }
    }

    @Override
    public double calculateFare(int numberOfTickets) {
        return farePerSeat * numberOfTickets;
    }

    public String getBusType() {
        return "Non-AC";
    }

    public String getSeatType() {
        return "seater";
    }

    @Override
    public int[] getBookedSeats() {
        return bookedSeats.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public int getTotalSeats() {
        return totalSeats;
    }


}

class NonAcSleeperBus implements Bus {
    private final int totalSeats = 30;
    private final Set<Integer> bookedSeats = new HashSet<>();
    private final double farePerSeat = 900.00;

    @Override
    public void viewSeats() {
        System.out.println("Non-AC Sleeper Bus - Available Seats:");
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i)) {
                System.out.print(i + " ");
            } else {
                System.out.print("X ");
            }

            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean isSeatAvailable(int seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    @Override
    public int[] bookTickets(int numberOfTickets) {
        int[] bookedSeatsArray = new int[numberOfTickets];
        for (int i = 1; i <= totalSeats; i++) {
            if (isSeatAvailable(i) && numberOfTickets > 0) {
                bookedSeats.add(i);
                bookedSeatsArray[numberOfTickets - 1] = i;
                numberOfTickets--;
            }
        }
        return bookedSeatsArray;
    }

    @Override
    public void cancelTickets(int[] bookedTickets) {
        for (int seatNumber : bookedTickets) {
            bookedSeats.remove(seatNumber);
        }
    }

    @Override
    public double calculateFare(int numberOfTickets) {
        return farePerSeat * numberOfTickets;
    }

    public String getBusType() {
        return "Non-AC";
    }

    public String getSeatType() {
        return "sleeper";
    }

    @Override
    public int[] getBookedSeats() {
        return bookedSeats.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public int getTotalSeats() {
        return totalSeats;
    }

}

class BusFactory {
    public Bus createBus(String busType, String seatType) {
        if ("AC".equalsIgnoreCase(busType)) {
            if ("seater".equalsIgnoreCase(seatType)) {
                return new AcSeaterBus();
            } else if ("sleeper".equalsIgnoreCase(seatType)) {
                return new AcSleeperBus();
            }
        } else if ("Non-AC".equalsIgnoreCase(busType)) {
            if ("seater".equalsIgnoreCase(seatType)) {
                return new NonAcSeaterBus();
            } else if ("sleeper".equalsIgnoreCase(seatType)) {
                return new NonAcSleeperBus();
            }
        }

        throw new IllegalArgumentException("Unsupported bus type or seat type");
    }
}

class Customer {
    private int id;
    private String name;
    private String password;
    private int age;
    private char gender;
    private List<Ticket> bookedTickets;

    public Customer(int id, String name, String password, int age, char gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.bookedTickets = new ArrayList<>();
    }

    // Constructor without ID
    public Customer(String name, String password, int age, char gender) {
        this.id = generateRandomId();
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.bookedTickets = new ArrayList<>();
    }

    // Other getters and setters

    private int generateRandomId() {
        return new Random().nextInt(1000); // Generate a random customer ID
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}


class Ticket {
    private final int id;
    private final List<Integer> bookedSeats;
    private final Bus bus;
    private final double fare;
    private boolean isCancelled;

    public Ticket(List<Integer> bookedSeats, Bus bus, double fare) {
        this.id = new Random().nextInt(1000); // Generate a random ticket ID
        this.bookedSeats = bookedSeats;
        this.bus = bus;
        this.fare = fare;
        this.isCancelled = false;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public Bus getBus() {
        return bus;
    }

    public double getFare() {
        return fare;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}

class BusTicketSystem {
    public static Map<String, Bus> busMap = new HashMap<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Bus> buses = new ArrayList<>();

    public BusTicketSystem() {
        busMap.put("acseater", new AcSeaterBus());
        busMap.put("acsleeper", new AcSleeperBus());
        busMap.put("nonacseater", new NonAcSeaterBus());
        busMap.put("nonacsleeper", new NonAcSleeperBus());

    }

    public void signUp(Customer customer) {
        customers.add(customer);
    }

    public Customer login(int customerId, String password) {
        return customers.stream().filter(customer -> customer.getId() == customerId && customer.getPassword().equals(password)).findFirst().orElse(null);
    }

    public Ticket bookTickets(Customer customer, String busType, int numberOfTickets) {
        BusFactory busFactory = new BusFactory();
        Bus bus = BusTicketSystem.busMap.get(busType.toLowerCase());

        if (bus != null) {
            int[] bookedSeats = bus.bookTickets(numberOfTickets);

            if (bookedSeats.length == numberOfTickets) {
                double fare = bus.calculateFare(numberOfTickets);
                Ticket ticket = new Ticket(Arrays.stream(bookedSeats).boxed().collect(Collectors.toList()), bus, fare);
                customer.getBookedTickets().add(ticket);
                return ticket;
            }
        }

        return null;
    }

    public void cancelTicket(Customer customer, Ticket ticket) {
        if (!ticket.isCancelled() && customer.getBookedTickets().contains(ticket)) {
            Bus bus = ticket.getBus();
            bus.cancelTickets(ticket.getBookedSeats().stream().mapToInt(Integer::intValue).toArray());
            ticket.setCancelled(true);
            customer.getBookedTickets().remove(ticket);
        }
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void showBusSummary() {
        Collection<Bus> buses = BusTicketSystem.busMap.values();
        for (Bus bus : buses) {
            System.out.println("Bus Type: " + bus.getBusType());
            System.out.println("Seat Type: " + bus.getSeatType());
            bus.viewSeats();
            int availableSeats = bus.getTotalSeats() - bus.getBookedSeats().length; // Calculate available seats
            System.out.println("Available Seats: " + availableSeats);
            System.out.println("------------------------------");
        }
    }

}


class Mainn {
    public static void main(String[] args) {
        BusTicketSystem ticketSystem = new BusTicketSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Bus Ticket System!");
            System.out.println("1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your gender (M/F): ");
                    char gender = scanner.nextLine().charAt(0);

                    Customer customer = new Customer(name, password, age, gender);
                    ticketSystem.signUp(customer);

                    System.out.println(" Customer Id " + customer.getId() + "sign up successful!");
                    break;

                case 2:
                    System.out.print("Enter your customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();

                    Customer loggedInCustomer = ticketSystem.login(customerId, loginPassword);
                    if (loggedInCustomer != null) {
                        System.out.println("Login successful!");
                        interactWithCustomer(scanner, ticketSystem, loggedInCustomer);
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Bus Ticket System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void interactWithCustomer(Scanner scanner, BusTicketSystem ticketSystem, Customer customer) {
        while (true) {
            System.out.println("Welcome, " + customer.getName() + "!");
            System.out.println("1. Book tickets");
            System.out.println("2. Cancel a ticket");
            System.out.println("3. Show bus summary");
            System.out.println("4. Log out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter bus type (AC/Non-AC): ");
                    String busType = scanner.nextLine();
                    System.out.print("Enter the number of tickets to book: ");
                    int numberOfTickets = scanner.nextInt();
                    scanner.nextLine();
                    Ticket bookedTicket = ticketSystem.bookTickets(customer, busType, numberOfTickets);
                    if (bookedTicket != null) {
                        System.out.println("Booking successful!");
                        System.out.println("Ticket Details:");
                        System.out.println("Ticket ID: " + bookedTicket.getId());
                        System.out.println("Booked Seats: " + bookedTicket.getBookedSeats());
                        System.out.println("Total Fare: Rs." + bookedTicket.getFare());
                    } else {
                        System.out.println("Booking failed.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the ticket ID to cancel: ");
                    int ticketId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    for (Ticket ticket : customer.getBookedTickets()) {
                        if (ticket.getId() == ticketId) {
                            ticketSystem.cancelTicket(customer, ticket);
                            System.out.println("Ticket canceled.");
                            break;
                        }
                    }
                    break;

                case 3:
                    ticketSystem.showBusSummary();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

