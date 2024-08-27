package Airline.model;

public class Ticket {
    private static int ticketCounter = 0;
    private String ticketNumber;
    private Flight flight;
    private Passenger passenger;
    private Seat seat;
    private double price;

    public Ticket(Flight flight, Passenger passenger, Seat seat, double price) {
        this.ticketNumber = "T" + (++ticketCounter);
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.price = price;
        passenger.assignTicket(ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket: " + ticketNumber + ", Flight: " + flight.getFlightNumber() + ", Passenger: " + passenger + ", Seat: " + seat + ", Price: " + price;
    }
}
