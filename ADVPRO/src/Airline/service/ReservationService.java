package Airline.service;

import Airline.model.Flight;
import Airline.model.Passenger;
import Airline.model.SeatType;
import Airline.model.Ticket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationService {
    private Map<String, Flight> flights = new ConcurrentHashMap<>();
    private Lock lock = new ReentrantLock();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public Ticket reserveSeat(String flightNumber, Passenger passenger, SeatType seatType, Date bookingDate) {
        lock.lock();
        try {
            Flight flight = flights.get(flightNumber);
            if (flight != null) {
                BookingService bookingService = new BookingService(this);
                return bookingService.bookSeat(flight, passenger, seatType, bookingDate);
            }
            throw new RuntimeException("Flight not found: " + flightNumber);
        } finally {
            lock.unlock();
        }
    }

    public void cancelReservation(String flightNumber, Passenger passenger, String seatNumber) {
        lock.lock();
        try {
            Flight flight = flights.get(flightNumber);
            if (flight != null) {
                BookingService bookingService = new BookingService(this);
                bookingService.cancelSeat(flight, passenger, seatNumber);
            } else {
                throw new RuntimeException("Flight not found: " + flightNumber);
            }
        } finally {
            lock.unlock();
        }
    }
}
