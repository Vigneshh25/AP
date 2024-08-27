package Airline.model;


import Airline.strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {
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
        this.seatMap = new SeatMap(10, 5, 3);
        this.passengers = new ArrayList<>();
        this.baggageList = new ArrayList<>();
        this.crew = new ArrayList<>();
        this.pricingStrategy = pricingStrategy;
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

    public void assignPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void assignCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public void addBaggage(Passenger passenger, double weight) {
        baggageList.add(new Baggage(passenger, weight));
    }

    @Override
    public String toString() {
        return "Flight: " + flightNumber + ", Aircraft: " + aircraft + ", Schedule: " + schedule + ", Seat Map: " + seatMap;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
