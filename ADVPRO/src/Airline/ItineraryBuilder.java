package Airline;

class ItineraryBuilder {
    private Itinerary itinerary;

    public ItineraryBuilder() {
        this.itinerary = new Itinerary();
    }

    public ItineraryBuilder addFlight(Flight flight) {
        this.itinerary.addFlight(flight);
        return this;
    }

    public ItineraryBuilder addPassenger(Passenger passenger) {
        this.itinerary.addPassenger(passenger);
        return this;
    }

    public Itinerary build() {
        return this.itinerary;
    }
}
