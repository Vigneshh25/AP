package BookMyshow.services;


import BookMyshow.entities.Seat;

import java.util.List;

public interface ShowService {
    boolean checkAvailability(String showId, List<Seat> seats);
}
