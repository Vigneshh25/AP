package BookMyshow.entities;

public class Seat {
    private String seatId;
    private String screenId;
    private int row;
    private int number;
    private SeatType seatType;

    public Seat(String seatId, String screenId, int row, int number, SeatType seatType) {
        this.seatId = seatId;
        this.screenId = screenId;
        this.row = row;
        this.number = number;
        this.seatType = seatType;
    }

    public double getPrice() {
        return seatType.getPrice();
    }
}
