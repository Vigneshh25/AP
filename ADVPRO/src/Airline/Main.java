package Airline;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialize Notification Service
        NotificationService notificationService = new NotificationService();

        // Create Admin
        Admin admin = new Admin(notificationService);

        // Create Aircraft
        Aircraft aircraft = new Aircraft("Boeing 737", 180);

        // Create Schedule
        Schedule schedule = new Schedule(new Date(), new Date(), new Airport("JFK"), new Airport("LAX"));

        // Create Flight Factory
        FlightFactory flightFactory = new ConcreteFlightFactory();

        // Create Flight
        Flight flight = admin.createFlight("AI123", aircraft, schedule, flightFactory);

        // Add Flight to Reservation Service
        ReservationService reservationService = new ReservationService();
        reservationService.addFlight(flight);

        // Create Passengers
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Jane Doe");

        // Reserve Seats and Generate Tickets
        Ticket ticket1 = reservationService.reserveSeat("AI123", passenger1);
        Ticket ticket2 = reservationService.reserveSeat("AI123", passenger2);

        // Check Flight Passengers
        System.out.println("Passengers on flight " + flight + ": " + flight.getPassengers());

        // Print Tickets
        System.out.println("Ticket 1: " + ticket1);
        System.out.println("Ticket 2: " + ticket2);

        // Cancel Reservation
        reservationService.cancelReservation("AI123", passenger1);
        System.out.println("Passengers after cancellation on flight " + flight + ": " + flight.getPassengers());

        // Create Itinerary
        ItineraryBuilder itineraryBuilder = new ItineraryBuilder();
        Itinerary itinerary = itineraryBuilder.addFlight(flight).addPassenger(passenger2).build();
        System.out.println("Created Itinerary: " + itinerary);

        // Payment
        PaymentStrategy paymentStrategy = new CreditCardPayment();
        paymentStrategy.pay(100.0);

        // Assign Pilot and Crew
        Pilot pilot = new Pilot("Captain America");
        CrewMember crewMember = new CrewMember("Crew Member 1");
        flight.assignPilot(pilot);
        flight.assignCrew(Arrays.asList(crewMember));

        // Admin Cancels Flight
        admin.cancelFlight(flight);

        // Add Observer
        Customer customerObserver = new Customer("customer@example.com");
        notificationService.addObserver(customerObserver);
        notificationService.notifyAllObservers("Test Notification");

        // Test Security (Authentication and Authorization would typically be more complex)
        // Here is a simple example for demonstration:
        System.out.println("Testing Security...");
        // Ideally, you'd have a SecurityService class to handle this
        System.out.println("Admin authenticated and authorized.");
    }
}
