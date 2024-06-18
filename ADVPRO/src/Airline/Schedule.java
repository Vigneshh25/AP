package Airline;

import java.util.Date;

class Schedule {
    private Date departureTime;
    private Date arrivalTime;
    private Airport source;
    private Airport destination;

    public Schedule(Date departureTime, Date arrivalTime, Airport source, Airport destination) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "From: " + source + " To: " + destination + " Departure: " + departureTime + " Arrival: " + arrivalTime;
    }
}
