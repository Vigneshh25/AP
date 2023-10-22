package TrainBookingWithCabin;


import java.util.*;

public class RailwayTicketReservation {
    public static List<Passenger> bookedTickets = new ArrayList<>();
    public static List<Passenger> racTickets = new ArrayList<>();
    public static List<Passenger> waitingListTickets = new ArrayList<>();
    public static Map<Integer, Ticket> ticketsMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    private static final List<Cabin> cabins = new ArrayList<>();
    private static final List<Passenger> passengers = new ArrayList<>();

    public static void main(String[] args) {

        Cabin cabin1 = new Cabin(1);
        Cabin cabin2 = new Cabin(2);
        cabins.add(cabin1);
        cabins.add(cabin2);

        while (true) {
            System.out.println("Railway Ticket Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Modify Ticket");
            System.out.println("4. Print Booked Tickets");
            System.out.println("5. Print Available Tickets");
            System.out.println("6. Print RAC Tickets");
            System.out.println("7. Print Waiting List Tickets");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookMultipleTickets();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    modifyTicket(bookedTickets, scanner);
                    break;
                case 4:
                    int pnr = scanner.nextInt();
                    Ticket ticket = ticketsMap.get(pnr);
                    Ticket.printTicket(ticket);
                    break;
                case 5:
                    printAvailableTickets();
                    break;
                case 6:
                    printRACList(racTickets);
                    break;
                case 7:
                    printWaitingList(waitingListTickets);
                    break;
                case 8:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookMultipleTickets() {
        System.out.print("Enter the number of passengers (maximum 3): ");
        int numPassengers = scanner.nextInt();
        if (numPassengers <= 0 || numPassengers >= 4) {
            System.out.println("Invalid number of passengers. Maximum 3 passengers allowed.");
            return;
        }
        if (waitingListTickets.size() + numPassengers >= 4) {
            System.out.println("Tickets Not Available");
            return;
        }
        List<Passenger> bookedTicket = new ArrayList<>();
        List<Seat> seatList = new ArrayList<>();

        for (int i = 0; i < numPassengers; i++) {
            System.out.println("Passenger " + (i + 1) + ":");
            Passenger passenger = createPassengerObj();
            bookTicket(passenger);
            bookedTicket.add(passenger);
            seatList.add(passenger.getSeat());
        }
        int pnr = Ticket.generatePNR();
        ticketsMap.put(pnr, new Ticket(pnr, bookedTicket, seatList));
        System.out.println("PNR Number "+pnr +"  "+ticketsMap.get(pnr));
    }

    private static void cancelTicket() {
        System.out.print("Enter PNR Number Of the Ticket : ");
        int pnr = scanner.nextInt();
        Ticket ticket = ticketsMap.get(pnr);

        if (ticket != null) {
            List<Passenger> passengerList = ticket.getPassengers();
            System.out.println(passengerList.size());
            for (Passenger passenger : passengerList) {
                    Seat seat = passenger.getSeat();
                    if (seat != null) {
                        System.out.println("Cancelling ticket for passenger: " + passenger.getName());
                        seat.setPassenger(null);
                        seat.setAvailable(true);
                        passenger.setSeat(null);
                        if (passenger.isConfirmed()) {
                            promoteRACPassengerToConfirmed(seat);
                        } else if (passenger.isRAC()) {
                            promoteWaitingListPassengerToRAC(seat);
                        }
                        System.out.println("Ticket for passenger " + passenger.getName() + " has been successfully cancelled.");
                    }
                    else
                    {
                        System.out.println("Seat Not Found !!!");
                    }
            }
            ticketsMap.remove(pnr);
        } else {
            System.out.println("Ticket with PNR " + pnr + " not found.");
        }
    }


    private static void promoteRACPassengerToConfirmed(Seat seat) {
        if(racTickets.isEmpty())
            return;
        Passenger passenger = racTickets.remove(0);
        System.out.println("Promoting RAC passenger to Confirmed: " + passenger.getName());
        Seat racSeat = passenger.getSeat();
        racSeat.setPassenger(null);
        racSeat.setAvailable(true);

        seat.setPassenger(passenger);
        seat.setAvailable(false);

        passenger.setSeat(seat);
        passenger.setConfirmed(true);
        passenger.setRAC(false);

        int pnr = Ticket.generatePNR();
        ticketsMap.put(pnr, new Ticket(pnr, List.of(passenger), List.of(seat)));
        bookedTickets.add(passenger);
        passengers.add(passenger);
        promoteWaitingListPassengerToRAC(racSeat);
    }

    private static void promoteWaitingListPassengerToRAC(Seat seat) {

        if(waitingListTickets.isEmpty())
            return;
        Passenger passenger = waitingListTickets.remove(0);
        System.out.println("Promoting RAC passenger to Confirmed: " + passenger.getName());

        seat.setPassenger(passenger);
        seat.setAvailable(false);

        passenger.setSeat(seat);
        passenger.setRAC(true);
        bookedTickets.add(passenger);
        passengers.add(passenger);

    }
    private static void bookTicket(Passenger passenger) {

        for (Cabin cabin : cabins) {
            Seat availableSeat = cabin.getAvailableSeat(passenger.getPreferredBerth());
            if (availableSeat != null) {
                allocateSeat(passenger, availableSeat, true);
                System.out.println("Allocated preferred berth to passenger " + passenger.getName() + ": " + availableSeat.getSeatType() + " - Seat " + availableSeat.getSeatNumber());
                return;
            }
        }

        for (Cabin cabin : cabins) {
            Seat availableSeat = cabin.getAvailableConfirmedSeat();
            if (availableSeat != null) {
                allocateSeat(passenger, availableSeat, true);
                System.out.println("Allocated confirmed seat to passenger " + passenger.getName() + ": " + availableSeat.getSeatType() + " - Seat " + availableSeat.getSeatNumber());
                return;
            }
        }

        for (Cabin cabin : cabins) {
            Seat availableSeat = cabin.getAvailableRACSeat();
            if (availableSeat != null) {
                allocateSeat(passenger, availableSeat, false);
                System.out.println("Allocated RAC seat to passenger " + passenger.getName() + ": " + availableSeat.getSeatType() + " - Seat " + availableSeat.getSeatNumber());
                return;
            }
        }
        System.out.println("No seats available for passenger. Adding to the waiting list: " + passenger.getName());
        waitingListTickets.add(passenger);
    }


    private static void allocateSeat(Passenger passenger, Seat seat, boolean isConfirmed) {
        seat.setPassenger(passenger);
        seat.setAvailable(false);
        bookedTickets.add(passenger);
        passenger.setSeat(seat);
        passenger.setConfirmed(isConfirmed);
        if (isConfirmed) {
            passengers.add(passenger);
        }
        if (passenger.isRAC()) {
            racTickets.add(passenger);
        }
    }

    private static Passenger createPassengerObj() {

        System.out.println("Enter passenger name: ");
        String name = scanner.next();
        System.out.println("Enter passenger age: ");
        int age = scanner.nextInt();
        if (age < 0)
            System.out.println("Age cannot be negative. Please enter a valid age.");
         else if (age < 5)
            System.out.println("Children below 5 years are not eligible for booking.");

        System.out.println("Enter passenger gender (M/F): ");
        String genderInput = scanner.next().toUpperCase();
        Gender gender = null;
        if (genderInput.equals("M")) gender = Gender.MALE;
        else gender = Gender.FEMALE;

        System.out.println("Enter berth preference (Upper/Lower/Middle/Side Upper/Any): ");
        String preferBerth = scanner.next().toLowerCase();
        SeatType berthPreference = SeatType.ANY;
        switch (preferBerth.toLowerCase()) {
            case "upper" -> berthPreference = SeatType.UPPER_BERTH;
            case "lower" -> berthPreference = SeatType.LOWER_BERTH;
            case "middle" -> berthPreference = SeatType.MIDDLE_BERTH;
            case "sideupper" -> berthPreference = SeatType.SIDE_UPPER_BERTH;
            default -> System.out.println("Invalid berth preference. Defaulting to 'Any'.");
        }
        return new Passenger(name, age, gender, berthPreference);
    }


    private static void modifyTicket(List<Passenger> bookedTickets, Scanner scanner) {
        System.out.print("Enter the PNR number of the ticket you want to modify: ");
        int pnr = scanner.nextInt();
        Ticket ticket = ticketsMap.get(pnr);

        if (ticket != null) {
            List<Passenger> passengers = ticket.getPassengers();
            System.out.println("Current Passenger Details:");
            for (int i = 0; i < passengers.size(); i++) {
                Passenger passenger = passengers.get(i);
                System.out.println("Passenger " + (i + 1) + ": " + passenger);
            }

            System.out.print("Enter the passenger number to modify: ");
            int passengerNumber = scanner.nextInt();

            if (passengerNumber >= 1 && passengerNumber <= passengers.size()) {
                Passenger passenger = passengers.get(passengerNumber - 1);
                System.out.println("Enter new passenger details:");
                Passenger newPassenger = createPassengerObj();
                if (newPassenger != null) {
                    // Update passenger details
                    passenger.setName(newPassenger.getName());
                    passenger.setAge(newPassenger.getAge());
                    passenger.setGender(newPassenger.getGender());
                    passenger.setPreferredBerth(newPassenger.getPreferredBerth());
                    System.out.println("Passenger details updated successfully.");
                }
            } else {
                System.out.println("Invalid passenger number.");
            }
        } else {
            System.out.println("Ticket with PNR " + pnr + " not found.");
        }
    }


    private static void printBookedTickets(List<Passenger> bookedTickets) {
        if (bookedTickets.isEmpty()) {
            System.out.println("No tickets have been booked.");
        } else {
            System.out.println("Booked Tickets:");
            for (int i = 0; i < bookedTickets.size(); i++) {
                Passenger passenger = bookedTickets.get(i);
                System.out.println("Ticket " + (i + 1) + ": " + passenger);
            }
        }
    }

    private static void printAvailableTickets() {
        System.out.println("Available Tickets:");
        for (Cabin cabin : cabins) {
            for (Seat seat : cabin.getSeats()) {
                if (seat.isAvailable()) {
                    System.out.println("Cabin " + cabin.getCabinNumber() + ", Seat: " + seat.getSeatNumber() + ", Type: " + seat.getSeatType());
                }
            }
        }
    }

    private static void printRACList(List<Passenger> racTickets) {
        if (racTickets.isEmpty()) {
            System.out.println("No RAC tickets available.");
        } else {
            System.out.println("RAC Tickets:");
            for (int i = 0; i < racTickets.size(); i++) {
                Passenger passenger = racTickets.get(i);
                System.out.println("RAC Ticket " + (i + 1) + ": " + passenger);
            }
        }
    }

    private static void printWaitingList(List<Passenger> waitingListTickets) {
        if (waitingListTickets.isEmpty()) {
            System.out.println("No passengers on the waiting list.");
        } else {
            System.out.println("Waiting List Tickets:");
            for (int i = 0; i < waitingListTickets.size(); i++) {
                Passenger passenger = waitingListTickets.get(i);
                System.out.println("Waiting List Ticket " + (i + 1) + ": " + passenger);
            }
        }
    }


}