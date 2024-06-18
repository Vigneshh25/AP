package Airline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Flight {
    private String flightNumber;
    private Aircraft aircraft;
    private Schedule schedule;
    private SeatMap seatMap;
    private List<Passenger> passengers;
    private List<Baggage> baggageList;
    private Pilot pilot;
    private List<CrewMember> crew;
    private PricingStrategy pricingStrategy;

    public Flight(String flightNumber, Aircraft aircraft, Schedule schedule, PricingStrategy pricingStrategy) {
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.schedule = schedule;
        this.seatMap = new SeatMap(120, 30, 10);
        this.passengers = new ArrayList<>();
        this.baggageList = new ArrayList<>();
        this.crew = new ArrayList<>();
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket bookSeat(Passenger passenger, SeatType seatType, Date bookingDate) {
        List<Seat> availableSeats = seatMap.getAvailableSeats(seatType);
        if (availableSeats.isEmpty()) {
            throw new RuntimeException("No available seats for seat type: " + seatType);
        }
        Seat seat = availableSeats.get(0);
        double price = pricingStrategy.calculatePrice(seatType, bookingDate, availableSeats.size());
        Ticket ticket = new Ticket(this, passenger, seat, price);
        passengers.add(passenger);
        return ticket;
    }

    public void cancelSeat(Passenger passenger, String seatNumber) {
        Seat seat = seatMap.getSeat(seatNumber);
        if (seat == null || seat.isAvailable()) {
            throw new RuntimeException("Invalid seat cancellation request.");
        }
        passengers.remove(passenger);
    }

    public void assignPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void assignCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public void addBaggage(Passenger passenger, double weight) {
        baggageList.add(new Baggage(passenger, weight));
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public SeatMap getSeatMap() {
        return seatMap;
    }

    @Override
    public String toString() {
        return "Flight: " + flightNumber + ", Aircraft: " + aircraft + ", Schedule: " + schedule + ", Seat Map: " + seatMap;
    }
}
