package Airline;

import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightNumber;
    private Aircraft aircraft;
    private Schedule schedule;
    private List<Passenger> passengers;
    private Pilot pilot;
    private List<CrewMember> crew;

    public Flight(String flightNumber, Aircraft aircraft, Schedule schedule) {
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.schedule = schedule;
        this.passengers = new ArrayList<>();
        this.crew = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void assignPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void assignCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString() {
        return "Flight: " + flightNumber + ", Aircraft: " + aircraft + ", Schedule: " + schedule;
    }
}
