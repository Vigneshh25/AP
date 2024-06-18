package Airline;

abstract class FlightFactory {
    public abstract Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule, PricingStrategy pricingStrategy);
}

class ConcreteFlightFactory extends FlightFactory {
    @Override
    public Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule, PricingStrategy pricingStrategy) {
        return new Flight(flightNumber, aircraft, schedule,pricingStrategy);
    }
}
