package TrainBookingWithCabin;

public class Seat {
    public int cabinNumber ;
    private SeatType seatType;
    private int seatNumber;
    private boolean available;
    private Passenger passenger;


    public Seat(int cabinNumber,int seatNumber, SeatType seatType, boolean available) {
        this.cabinNumber = cabinNumber;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.available = available;
        this.passenger = null;
    }


    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        String passengerInfo = (passenger != null) ? passenger.getName() : "Empty";
        return "Seat{" +
                "seatType=" + seatType +
                ", seatNumber=" + seatNumber +
                ", available=" + available +
                ", passenger=" + passengerInfo +
                '}';
    }
}