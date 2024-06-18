package Airline;

import java.util.ArrayList;
import java.util.List;

class Itinerary {
    private List<Flight> flights;
    private List<Passenger> passengers;

    public Itinerary() {
        this.flights = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public String toString() {
        return "Itinerary [Flights=" + flights + ", Passengers=" + passengers + "]";
    }
}
