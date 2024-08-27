package Airline.model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private List<Flight> flights = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itinerary:\n");
        for (Flight flight : flights) {
            sb.append("Flight: ").append(flight.getFlightNumber()).append("\n");
        }
        for (Passenger passenger : passengers) {
            sb.append("Passenger: ").append(passenger.getName()).append("\n");
        }
        return sb.toString();
    }
}
