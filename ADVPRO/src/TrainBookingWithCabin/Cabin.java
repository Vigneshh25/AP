package TrainBookingWithCabin;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    int cabinNumber;
    static int LastSeat = 1;
    List<Seat> seats;

    public Cabin(int cabinNumber) {
        this.cabinNumber = cabinNumber;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            seats.add(new Seat(cabinNumber, LastSeat++, SeatType.LOWER_BERTH, true));
            seats.add(new Seat(cabinNumber, LastSeat++, SeatType.MIDDLE_BERTH, true));
            seats.add(new Seat(cabinNumber, LastSeat++, SeatType.UPPER_BERTH, true));
        }
        seats.add(new Seat(cabinNumber, LastSeat, SeatType.SIDE_LOWER_BERTH, true));
        seats.add(new Seat(cabinNumber, LastSeat++, SeatType.SIDE_LOWER_BERTH, true));
        seats.add(new Seat(cabinNumber, LastSeat++, SeatType.SIDE_UPPER_BERTH, true));
    }

    public int getCabinNumber() {
        return cabinNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getAvailableSeat(SeatType seatType) {
        for (Seat seat : seats) {
            if (seatType.equals(seat.getSeatType()) && seat.isAvailable()) {
                return seat;
            }
        }
        return null;
    }

    public Seat getAvailableConfirmedSeat() {
        for (Seat seat : seats) {
            if (seat.isAvailable() && seat.getSeatType() != SeatType.RAC && seat.getSeatType() != SeatType.SIDE_LOWER_BERTH && seat.getSeatType() != SeatType.WAITING_LIST) {
                return seat;
            }
        }
        return null;
    }

    public Seat getAvailableRACSeat() {
        for (Seat seat : seats) {
            if (seat.isAvailable() && seat.getSeatType() == SeatType.SIDE_LOWER_BERTH) {
                return seat;
            }
        }
        return null;
    }
    public Seat getAvailableLowerBerthSeat() {
        for (Seat seat : seats) {
            if (seat.isAvailable() && seat.getSeatType() == SeatType.LOWER_BERTH) {
                return seat;
            }
        }
        return null;
    }


}