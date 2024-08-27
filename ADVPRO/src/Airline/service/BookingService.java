package Airline.service;

import Airline.model.*;

import java.util.Date;
import java.util.List;

public class BookingService {
    private ReservationService reservationService;

    public BookingService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Ticket bookSeat(Flight flight, Passenger passenger, SeatType seatType, Date bookingDate) {
        List<Seat> availableSeats = flight.getSeatMap().getAvailableSeats(seatType);
        if (availableSeats.isEmpty()) {
            throw new RuntimeException("No available seats for seat type: " + seatType);
        }
        Seat seat = availableSeats.get(0);
        double price = flight.getPricingStrategy().calculatePrice(seatType, bookingDate, availableSeats.size());
        passenger.setSeat(seat);
        seat.assignPassenger(passenger);
        Ticket ticket = new Ticket(flight, passenger, seat, price);
        flight.getPassengers().add(passenger);
        return ticket;
    }

    public void cancelSeat(Flight flight, Passenger passenger, String seatNumber) {
        Seat seat = flight.getSeatMap().getSeat(seatNumber);
        if (seat == null || seat.isAvailable()) {
            throw new RuntimeException("Invalid seat cancellation request.");
        }
        passenger.setSeat(null);
        seat.assignPassenger(null);
        flight.getPassengers().remove(passenger);
    }
}
