package Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Passenger {
    static int totalLowerBerths = 21;
    static int totalSLBerths = 21;
    static int totalOtherBerths = 21;
    static int totalRACTickets = 18;
    static int totalWaitingListTickets = 10;
    static int seatNumberCounter = 1;

    String name;
    int age;
    String gender;
    String berthPreference;
    int seatNumber;

    public Passenger(String name, int age, String gender, String berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.seatNumber = seatNumberCounter++;
    }
}

public class RailwayTicketReservation {
    public static void main(String[] args) {

        List<Passenger> bookedTickets = new ArrayList<>();
        List<Passenger> racTickets = new ArrayList<>();
        List<Passenger> waitingListTickets = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

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
                    bookMultipleTickets(bookedTickets, racTickets, waitingListTickets, scanner);
                    break;
                case 2:
                    cancelTicket(bookedTickets, racTickets, waitingListTickets, scanner);
                    break;
                case 3:
                    modifyTicket(bookedTickets, scanner);
                    break;
                case 4:
                    printBookedTickets(bookedTickets);
                    break;
                case 5:
                    printAvailableTickets(Passenger.totalLowerBerths, Passenger.totalSLBerths, Passenger.totalOtherBerths, Passenger.totalRACTickets, Passenger.totalWaitingListTickets, bookedTickets, racTickets, waitingListTickets);
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

    private static void bookMultipleTickets(List<Passenger> bookedTickets, List<Passenger> racTickets, List<Passenger> waitingListTickets, Scanner scanner) {
        System.out.print("Enter the number of passengers (maximum 4): ");
        int numPassengers = scanner.nextInt();

        if (numPassengers <= 0 || numPassengers > 4) {
            System.out.println("Invalid number of passengers. Maximum 4 passengers allowed.");
            return;
        }

        for (int i = 0; i < numPassengers; i++) {
            System.out.println("Passenger " + (i + 1) + ":");
            bookTicket(bookedTickets, racTickets, waitingListTickets, scanner);
        }
    }

    private static void bookTicket(List<Passenger> bookedTickets, List<Passenger> racTickets, List<Passenger> waitingListTickets, Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String name = scanner.next();
        System.out.print("Enter passenger age: ");
        int age = scanner.nextInt();
        System.out.print("Enter passenger gender (M/F): ");
        String gender = scanner.next();
        System.out.print("Enter berth preference (Lower/SL/Other): ");
        String berthPreference = scanner.next();

        if (age < 5) {
            System.out.println("Children below 5 years are not eligible for booking. Their details are stored.");
            return;
        }

        if (age >= 60 || (gender.equalsIgnoreCase("F") && age > 0)) {
            berthPreference = "Lower";
        }

        if (bookedTickets.size() + racTickets.size() < Passenger.totalLowerBerths + Passenger.totalSLBerths + Passenger.totalOtherBerths) {
            if (bookedTickets.size() < Passenger.totalLowerBerths && berthPreference.equalsIgnoreCase("Lower")) {
                bookedTickets.add(new Passenger(name, age, gender, berthPreference));
            } else if (bookedTickets.size() < Passenger.totalSLBerths && berthPreference.equalsIgnoreCase("SL")) {
                bookedTickets.add(new Passenger(name, age, gender, berthPreference));
            } else if (bookedTickets.size() < Passenger.totalOtherBerths) {
                bookedTickets.add(new Passenger(name, age, gender, "Other"));
            } else {
                System.out.println("No available berths with the preferred choice. Booking in the next available berth.");
                bookedTickets.add(new Passenger(name, age, gender, "Other"));
            }
            System.out.println("Ticket booked successfully! Seat Number: " + Passenger.seatNumberCounter);
        } else if (racTickets.size() < 18) {
            racTickets.add(new Passenger(name, age, gender, "Side-Lower"));
            System.out.println("Ticket booked in RAC! Seat Number: " + Passenger.seatNumberCounter);
        } else if (waitingListTickets.size() < 10) {
            waitingListTickets.add(new Passenger(name, age, gender, "Waiting-List"));
            System.out.println("Ticket in waiting list.");
        } else {
            System.out.println("No tickets available.");
        }
    }

    private static void cancelTicket(List<Passenger> bookedTickets, List<Passenger> racTickets, List<Passenger> waitingListTickets, Scanner scanner) {
        System.out.print("Enter passenger name to cancel the ticket: ");
        String name = scanner.next();

        for (int i = 0; i < bookedTickets.size(); i++) {
            if (bookedTickets.get(i).name.equals(name)) {
                bookedTickets.remove(i);
                if (racTickets.size() > 0) {
                    bookedTickets.add(racTickets.get(0));
                    racTickets.remove(0);
                }
                if (waitingListTickets.size() > 0) {
                    racTickets.add(waitingListTickets.get(0));
                    waitingListTickets.remove(0);
                }
                System.out.println("Ticket canceled successfully!");
                return;
            }
        }

        System.out.println("Ticket not found for passenger: " + name);
    }

    private static void modifyTicket(List<Passenger> bookedTickets, Scanner scanner) {
        System.out.print("Enter passenger name to modify the ticket: ");
        String name = scanner.next();

        for (int i = 0; i < bookedTickets.size(); i++) {
            if (bookedTickets.get(i).name.equals(name)) {
                System.out.println("Modifying passenger: " + name);
                bookTicket(bookedTickets, new ArrayList<>(), new ArrayList<>(), scanner);
                System.out.println("Ticket modified successfully! Seat Number: " + Passenger.seatNumberCounter);
                return;
            }
        }

        System.out.println("Ticket not found for passenger: " + name);
    }

    private static void printBookedTickets(List<Passenger> bookedTickets) {
        System.out.println("Booked Tickets:");
        for (int i = 0; i < bookedTickets.size(); i++) {
            Passenger passenger = bookedTickets.get(i);
            System.out.println("Ticket " + (i + 1) + ":");
            System.out.println("Name: " + passenger.name);
            System.out.println("Age: " + passenger.age);
            System.out.println("Gender: " + passenger.gender);
            System.out.println("Berth Preference: " + passenger.berthPreference);
            System.out.println("Seat Number: " + passenger.seatNumber);
            System.out.println();
        }
        System.out.println("Total Tickets Booked: " + bookedTickets.size());
    }

    private static void printAvailableTickets(int totalLowerBerths, int totalSLBerths, int totalOtherBerths, int totalRACTickets, int totalWaitingListTickets, List<Passenger> bookedTickets, List<Passenger> racTickets, List<Passenger> waitingListTickets) {
        System.out.println("Available Tickets:");
        System.out.println("Lower Berths Available: " + (totalLowerBerths - countBerths(bookedTickets, "Lower")));
        System.out.println("SL Berths Available: " + (totalSLBerths - countBerths(bookedTickets, "SL")));
        System.out.println("Other Berths Available: " + (totalOtherBerths - countBerths(bookedTickets, "Other")));
        System.out.println("RAC Tickets Available: " + (18 - racTickets.size()));
        System.out.println("Waiting List Tickets Available: " + (10 - waitingListTickets.size()));
    }

    private static int countBerths(List<Passenger> passengers, String berthPreference) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.berthPreference.equalsIgnoreCase(berthPreference)) {
                count++;
            }
        }
        return count;
    }

    private static void printRACList(List<Passenger> racTickets) {
        System.out.println("RAC Tickets:");
        for (int i = 0; i < racTickets.size(); i++) {
            Passenger passenger = racTickets.get(i);
            System.out.println("Ticket " + (i + 1) + ":");
            System.out.println("Name: " + passenger.name);
            System.out.println("Age: " + passenger.age);
            System.out.println("Gender: " + passenger.gender);
            System.out.println("Berth Preference: " + passenger.berthPreference);
            System.out.println("Seat Number: " + passenger.seatNumber);
            System.out.println();
        }
        System.out.println("Total RAC Tickets: " + racTickets.size());
    }

    private static void printWaitingList(List<Passenger> waitingListTickets) {
        System.out.println("Waiting List Tickets:");
        for (int i = 0; i < waitingListTickets.size(); i++) {
            Passenger passenger = waitingListTickets.get(i);
            System.out.println("Ticket " + (i + 1) + ":");
            System.out.println("Name: " + passenger.name);
            System.out.println("Age: " + passenger.age);
            System.out.println("Gender: " + passenger.gender);
            System.out.println("Berth Preference: " + passenger.berthPreference);
            System.out.println("Seat Number: " + passenger.seatNumber);
            System.out.println();
        }
        System.out.println("Total Waiting List Tickets: " + waitingListTickets.size());
    }
}
