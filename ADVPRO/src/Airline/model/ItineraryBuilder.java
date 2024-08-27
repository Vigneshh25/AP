package Airline.model;

public class ItineraryBuilder {
    private Itinerary itinerary;

    public ItineraryBuilder() {
        this.itinerary = new Itinerary();
    }

    public ItineraryBuilder addFlight(Flight flight) {
        itinerary.addFlight(flight);
        return this;
    }

    public ItineraryBuilder addPassenger(Passenger passenger) {
        itinerary.addPassenger(passenger);
        return this;
    }

    public Itinerary build() {
        return itinerary;
    }
}
