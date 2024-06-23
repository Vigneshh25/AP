package BookMyshow.entities;


import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private String bookingId;
    private String showId;
    private String userId;
    private List<Seat> seats;
    private LocalDateTime bookingTime;
    private double totalAmount;

    public Booking(String bookingId, String showId, String userId, List<Seat> seats, double totalAmount) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.userId = userId;
        this.seats = seats;
        this.bookingTime = LocalDateTime.now();
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}




