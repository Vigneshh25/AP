package Airline.model;

public class Passenger {
    private String name;
    private String ticketNumber;
    private Seat seat;

    public Passenger(String name) {
        this.name = name;
    }

    public void assignTicket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getName() {
        return name;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
