package BookMyshow.entities;

import java.time.LocalDateTime;
import java.util.Map;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {
    private String showId;
    private String movieId;
    private String theaterId;
    private String screenId;
    private LocalDateTime time;
    private Map<Seat, Boolean> seatAvailability;

    public Show(String showId, String movieId, String theaterId, String screenId, LocalDateTime time, Map<Seat, Boolean> seatAvailability) {
        this.showId = showId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.screenId = screenId;
        this.time = time;
        this.seatAvailability = seatAvailability;
    }

    // Getters and setters

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Map<Seat, Boolean> getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(Map<Seat, Boolean> seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    public boolean isSeatAvailable(Seat seat) {
        return seatAvailability.getOrDefault(seat, false);
    }

    public void reserveSeat(Seat seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
        } else {
            throw new IllegalArgumentException("Seat is already reserved");
        }
    }
}


