package Airline;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

class ReservationService {
    private Map<String, Flight> flights = new ConcurrentHashMap<>();
    private Lock lock = new ReentrantLock();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public Ticket reserveSeat(String flightNumber, Passenger passenger) {
        lock.lock();
        try {
            Flight flight = flights.get(flightNumber);
            if (flight != null) {
                flight.addPassenger(passenger);
                return new Ticket(flight, passenger);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void cancelReservation(String flightNumber, Passenger passenger) {
        lock.lock();
        try {
            Flight flight = flights.get(flightNumber);
            if (flight != null) {
                flight.getPassengers().remove(passenger);
            }
        } finally {
            lock.unlock();
        }
    }
}

