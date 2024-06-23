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

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public double getPrice() {
        return seatType.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        return seatId.equals(seat.seatId);
    }

    @Override
    public int hashCode() {
        return seatId.hashCode();
    }
}

