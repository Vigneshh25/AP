package Airline;

import Airline.model.*;
import Airline.factory.ConcreteFlightFactory;
import Airline.factory.FlightFactory;
import Airline.service.*;
import Airline.model.SeatType;
import Airline.model.Customer;
import Airline.model.Observer;
import Airline.strategy.*;
import Airline.strategy.PaymentStrategy;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize Notification Service
            NotificationService notificationService = new NotificationService();

            // Create Admin Service
            AdminService adminService = new AdminService(notificationService);

            // Create Aircraft
            Aircraft aircraft = new Aircraft("Boeing 737", 180);

            // Create Schedule
            Schedule schedule = new Schedule(new Date(), new Date(), new Airport("JFK"), new Airport("LAX"));

            // Create Flight Factory
            FlightFactory flightFactory = new ConcreteFlightFactory();

            // Create Pricing Strategy
            PricingStrategy pricingStrategy = new DynamicPricingStrategy();

            // Create Flight using Admin Service
            Flight flight = adminService.createFlight("AI123", aircraft, schedule, flightFactory, pricingStrategy);

            // Add Flight to Reservation Service
            ReservationService reservationService = new ReservationService();
            reservationService.addFlight(flight);

            // Create Passengers
            Passenger passenger1 = new Passenger("John Doe");
            Passenger passenger2 = new Passenger("Jane Doe");

            // Reserve Seats and Generate Tickets
            Ticket ticket1 = reservationService.reserveSeat("AI123", passenger1, SeatType.ECONOMY, new Date());
            Ticket ticket2 = reservationService.reserveSeat("AI123", passenger2, SeatType.BUSINESS, new Date());

            // Print Flight Details and Passenger Information
            System.out.println("Flight Details:");
            System.out.println("Passengers on flight:");
            for (Seat seat : flight.getSeatMap().getAllSeats()) {
                if (!seat.isAvailable()) {
                    System.out.println("- " + seat.getPassenger().getName() + " | " + seat);
                }
            }

            // Print Tickets
            System.out.println("\nTickets:");
            System.out.println("- Ticket 1: " + ticket1);
            System.out.println("- Ticket 2: " + ticket2);

            // Cancel Reservation Example (Handling Exception)
            try {
                reservationService.cancelReservation("AI123", passenger1, "C1");
                System.out.println("\nAfter cancellation, passengers on flight:");
                for (Seat seat : flight.getSeatMap().getAllSeats()) {
                    if (!seat.isAvailable()) {
                        System.out.println("- " + seat.getPassenger().getName() + " | " + seat);
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("\nFailed to cancel reservation: " + e.getMessage());
            }

            // Create Itinerary
            ItineraryBuilder itineraryBuilder = new ItineraryBuilder();
            Itinerary itinerary = itineraryBuilder.addFlight(flight).addPassenger(passenger2).build();
            System.out.println("\nCreated Itinerary:");
            System.out.println(itinerary);

            // Payment Example
            PaymentStrategy paymentStrategy = new CreditCardPayment();
            paymentStrategy.pay(100.0);

            // Assign Pilot and Crew
            Pilot pilot = new Pilot("Captain America");
            CrewMember crewMember = new CrewMember("Crew Member 1");
            flight.assignPilot(pilot);
            flight.assignCrew(Arrays.asList(crewMember));

            // Add Baggage
            flight.addBaggage(passenger1, 20.0); // Passenger 1 with 20kg baggage
            flight.addBaggage(passenger2, 15.0); // Passenger 2 with 15kg baggage

            // Admin Cancels Flight
            adminService.cancelFlight(flight);

            // Add Observer
            Observer customerObserver = new Customer("customer@example.com");
            notificationService.addObserver(customerObserver);
            notificationService.notifyAllObservers("Test Notification");

            // Test Security (Authentication and Authorization would typically be more complex)
            SecurityService securityService = new SecurityService();
            securityService.authenticateAdmin("admin", "password");

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
