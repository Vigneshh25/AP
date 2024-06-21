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







