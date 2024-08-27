package BookMyshow.services;


import BookMyshow.entities.Seat;
import BookMyshow.entities.Show;
import BookMyshow.repositories.ShowRepository;

import java.util.List;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public boolean checkAvailability(String showId, List<Seat> seats) {
        Show show = showRepository.getShowById(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found");
        }

        for (Seat seat : seats) {
            if (!show.isSeatAvailable(seat)) {
                return false;
            }
        }
        return true;
    }

}

