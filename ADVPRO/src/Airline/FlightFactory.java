package Airline;

abstract class FlightFactory {
    public abstract Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule);
}

class ConcreteFlightFactory extends FlightFactory {
    @Override
    public Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule) {
        return new Flight(flightNumber, aircraft, schedule);
    }
}
