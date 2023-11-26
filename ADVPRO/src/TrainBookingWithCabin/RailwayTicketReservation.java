package TrainBookingWithCabin;

import java.util.*;

public class RailwayTicketReservation {

    public static Map<Integer,Ticket> ticketsMap = new HashMap<>();
    public static List<Passenger> bookedTickets = new ArrayList<>();
    public static List<Passenger> RACTickets = new ArrayList<>();
    public static List<Passenger> waitingListTickets = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    private static final List<Cabin> cabins = new ArrayList<>();

    private static List<Coach> coachList = new ArrayList<>();
    private static final List<Passenger> CNFPpassengers = new ArrayList<>();
    static int cabinNumber = 1;

    public static void main(String[] args) {

        Cabin cabin1 = new Cabin(cabinNumber++);
        Cabin cabin2 = new Cabin(cabinNumber++);
        cabins.add(cabin1);
        cabins.add(cabin2);

        while (true) {
            System.out.println("Railway Ticket Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Print Booked Tickets For PNR");
            System.out.println("4. Print Available Tickets");
            System.out.println("5. Print RAC Tickets");
            System.out.println("6. Print Waiting List Tickets");
            System.out.println("7. Add New Cabin");
            System.out.println("8. Print Seats With Status");
            System.out.println("9. Add New Coach");
            System.out.println("10. Exit");
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
                    int pnr = scanner.nextInt();
                    Ticket ticket = ticketsMap.get(pnr);
                    Ticket.printTicket(ticket);
                    break;
                case 4:
                    printAvailableTickets();
                    break;
                case 5:
                    printRACList(RACTickets);
                    break;
                case 6:
                    printWaitingList(waitingListTickets);
                    break;
                case 7:
                    addCabin();
                    break;
                case 8:
                    printAllSeatsWithStatus();
                    break;
                case 9:
                    addNewCoach();
                    break;
                case 10:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewCoach() {
        for(int i=0;i<2;i++)
        {
            Cabin cabin = new Cabin(cabinNumber++);
            cabins.add(cabin);
        }
    }

    private static void addCabin() {
        Cabin cabin = new Cabin(cabinNumber++);
        cabins.add(cabin);
    }

    private static void bookMultipleTickets() {
        if(waitingListTickets.size()>=cabins.size()*2)
        {
            System.out.println("Ticket Are UnAvailable .Please Come Back Later !!");
            return;
        }
        System.out.print("Enter the number of passengers (maximum 3): ");
        int numPassengers = scanner.nextInt();
        if (numPassengers <= 0 || numPassengers >= 4) {
            System.out.println("Invalid number of passengers. Maximum 4 passengers allowed.");
            return;
        }
        if (!waitingListTickets.isEmpty()&&waitingListTickets.size() + numPassengers >= cabins.size()*2) {
            System.out.println("Tickets Not Available");
            return;
        }
        List<Passenger> bookedTicket = new ArrayList<>();

        for (int i = 0; i < numPassengers; i++) {
            System.out.println("Passenger " + (i + 1) + ":");
            Passenger passenger = createPassengerObj();
            if(passenger!=null) {
                bookTicket(passenger);
                bookedTicket.add(passenger);
            }
        }
        if(bookedTicket.isEmpty())
        {
            System.out.println("No Tickets Has Been Booked !!");
            return;
        }
        Passenger.id = 0;
        int pnr = Ticket.generatePNR();
        ticketsMap.put(pnr, new Ticket(pnr,bookedTicket));
        System.out.println("PNR Number "+pnr +"  "+ticketsMap.get(pnr));
    }

    private static void cancelTicket() {
        System.out.print("Enter PNR Number Of the Ticket : ");
        int pnr = scanner.nextInt();
        if(!ticketsMap.containsKey(pnr))
        {
            System.out.println("Invalid PNR Number Please Verify It.");
            return;
        }
        Ticket ticket = ticketsMap.get(pnr);
        System.out.println("Are You Looking to Cancel All Ticket (Yes/NO) Ticket Associated With Your PNR is "+ticket.getPassengers().size());

        boolean cancelAll = scanner.next().toLowerCase().equals("yes");


        if(cancelAll)
        {
            cancelAllTickets(ticket);
            ticketsMap.remove(pnr);
        }
        else
        {
            cancelSingleTicket(ticket);
        }
    }

    private static void cancelSingleTicket(Ticket ticket) {
        List<Passenger> passengerList = ticket.getPassengers();
        System.out.println(passengerList);
        System.out.println("Please Enter the PassengerId :");
        int passengerId = scanner.nextInt();
        if(passengerId>=passengerList.size())
        {
            System.out.println("Sorry Incorrect PassengerId Please Verify");
            return;
        }
        Passenger passenger = passengerList.remove(passengerId);
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
            ticketsMap.put(ticket.getPNR(),new Ticket(ticket.getPNR(),passengerList));
        }
    }

    private static void cancelAllTickets(Ticket ticket) {
        List<Passenger> passengerList = ticket.getPassengers();
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
    }


    private static void promoteRACPassengerToConfirmed(Seat seat) {
        if(RACTickets.isEmpty())
            return;
        Passenger passenger = RACTickets.remove(0);
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
        ticketsMap.put(pnr, new Ticket(pnr, List.of(passenger)));
        bookedTickets.add(passenger);
        CNFPpassengers.add(passenger);
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
        RACTickets.add(passenger);
        CNFPpassengers.add(passenger);

    }
    private static void bookTicket(Passenger passenger) {

        if(passenger.getAge()>=60)
        {
            for (Cabin cabin : cabins) {
                Seat availableSeat = cabin.getAvailableLowerBerthSeat();
                if (availableSeat != null) {
                    allocateSeat(passenger, availableSeat, true);
                    System.out.println("Lower Berth is Given to the passenger " + passenger.getName() + ": " + availableSeat.getSeatType() + " - Seat " + availableSeat.getSeatNumber());
                    return;
                }
            }
        }

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
                passenger.setRAC(true);
                RACTickets.add(passenger);
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
            CNFPpassengers.add(passenger);
        }
    }

    private static Passenger createPassengerObj() {

        System.out.println("Enter passenger name: ");
        String name = scanner.next();
        System.out.println("Enter passenger age: ");
        int age = scanner.nextInt();

        if (age <= 5){ System.out.println("Children below 5 years are not eligible for booking.");return null;}

        System.out.println("Enter passenger gender (M/F): ");
        String genderInput = scanner.next().toUpperCase();

        System.out.println("Enter berth preference (Upper/Lower/Middle/Side Upper/Any): ");
        String preferBerth = scanner.next().toLowerCase();
        SeatType berthPreference = SeatType.ANY;
        switch (preferBerth.toLowerCase()) {
            case "upper" -> berthPreference = SeatType.UPPER_BERTH;
            case "lower" -> berthPreference = SeatType.LOWER_BERTH;
            case "middle" -> berthPreference = SeatType.MIDDLE_BERTH;
            case "sideupper" -> berthPreference = SeatType.SIDE_UPPER_BERTH;
            default -> System.out.println("Berth Preference Defaulting to Any.");
        }
        return new Passenger(name, age, berthPreference);
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
    private static void printAllSeatsWithStatus() {
        for (Cabin cabin : cabins) {
            for (Seat seat : cabin.getSeats()) {
                    System.out.println("Cabin " + cabin.getCabinNumber() + ", Seat: " + seat.getSeatNumber() + ", Type: " + seat.getSeatType()+ ", Available: "+(seat.isAvailable()?"Yes":"NO"));
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