package Airline.factory;

import Airline.model.Flight;
import Airline.model.Aircraft;
import Airline.model.Schedule;
import Airline.strategy.PricingStrategy;

public class ConcreteFlightFactory extends FlightFactory {
    @Override
    public Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule, PricingStrategy pricingStrategy) {
        return new Flight(flightNumber, aircraft, schedule, pricingStrategy);
    }
}
