package TrainBookingWithCabin;

import java.util.ArrayList;
import java.util.List;

class Cabin {
    private final int cabinNumber;
    private final List<Seat> seats;

    public Cabin(int cabinNumber) {
        this.cabinNumber = cabinNumber;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            SeatType type = SeatType.LOWER_BERTH;
            if (i <= 4) {
                type = SeatType.UPPER_BERTH;
            } else if (i <= 8) {
                type = SeatType.MIDDLE_BERTH;
            } else if (i <= 11) {
                type = SeatType.SIDE_UPPER_BERTH;
            } else if (i == 18) {
                type = SeatType.SIDE_LOWER_BERTH;
            }
            seats.add(new Seat(cabinNumber,i, type, true));
        }
    }

    public int getCabinNumber() {
        return cabinNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
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

    public Seat getSeatByPassenger(Passenger passenger) {
        for (Seat seat : seats) {
            if (seat.getPassenger() == passenger) {
                return seat;
            }
        }
        return null;
    }

    public void updateAvailability() {
        for (Seat seat : seats) {
            seat.setAvailable(seat.getPassenger() == null);
        }
    }


}