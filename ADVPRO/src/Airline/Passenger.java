package Airline;

class Pilot {
    private String name;

    public Pilot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class CrewMember {
    private String name;

    public CrewMember(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Airport {
    private String code;

    public Airport(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
class Passenger {
    private String name;
    private String ticketNumber;

    public Passenger(String name) {
        this.name = name;
    }

    public void assignTicket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Ticket {
    private static int ticketCounter = 0;
    private String ticketNumber;
    private Flight flight;
    private Passenger passenger;

    public Ticket(Flight flight, Passenger passenger) {
        this.ticketNumber = "T" + (++ticketCounter);
        this.flight = flight;
        this.passenger = passenger;
        passenger.assignTicket(ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket: " + ticketNumber + ", Flight: " + flight.getFlightNumber() + ", Passenger: " + passenger;
    }
}






