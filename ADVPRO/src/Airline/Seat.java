package Airline;

enum SeatType {
    ECONOMY, BUSINESS, FIRST
}

public class Seat {
    private String seatNumber;
    private SeatType seatType;
    private Passenger passenger; // New field to hold Passenger information

    public Seat(String seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.passenger = null; // Initially, no passenger assigned
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Passenger getPassenger() {
        return passenger;
    }


    public void assignPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void removePassenger() {
        this.passenger = null;
    }

    public boolean isAvailable() {
        return passenger == null;
    }

    @Override
    public String toString() {
        return "Seat: " + seatNumber + " (" + seatType + ")";
    }
}

