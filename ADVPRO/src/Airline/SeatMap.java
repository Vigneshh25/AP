package Airline;

import java.util.*;
import java.util.stream.Collectors;

class SeatMap {
    private Map<String, Seat> seats;

    public SeatMap(int economySeats, int businessSeats, int firstClassSeats) {
        seats = new HashMap<>();
        char row = 'A';
        int seatNumber = 1;
        for (int i = 0; i < firstClassSeats; i++) {
            seats.put(row + String.valueOf(seatNumber), new Seat(row + String.valueOf(seatNumber), SeatType.FIRST));
            seatNumber++;
            if (seatNumber > 6) { row++; seatNumber = 1; }
        }
        for (int i = 0; i < businessSeats; i++) {
            seats.put(row + String.valueOf(seatNumber), new Seat(row + String.valueOf(seatNumber), SeatType.BUSINESS));
            seatNumber++;
            if (seatNumber > 6) { row++; seatNumber = 1; }
        }
        for (int i = 0; i < economySeats; i++) {
            seats.put(row + String.valueOf(seatNumber), new Seat(row + String.valueOf(seatNumber), SeatType.ECONOMY));
            seatNumber++;
            if (seatNumber > 6) { row++; seatNumber = 1; }
        }
    }

    public Seat getSeat(String seatNumber) {
        return seats.get(seatNumber);
    }

    public List<Seat> getAvailableSeats(SeatType seatType) {
        return seats.values().stream()
                .filter(seat -> seat.getSeatType() == seatType && seat.isAvailable())
                .collect(Collectors.toList());
    }

    public Collection<Seat> getAllSeats() {
        return  seats.values();
    }

    @Override
    public String toString() {
        return seats.values().toString();
    }
}
