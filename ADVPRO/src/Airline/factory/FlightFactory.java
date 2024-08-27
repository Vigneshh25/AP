package Airline.factory;

import Airline.model.Flight;
import Airline.model.Aircraft;
import Airline.model.Schedule;
import Airline.strategy.PricingStrategy;

public abstract class FlightFactory {
    public abstract Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule, PricingStrategy pricingStrategy);
}
