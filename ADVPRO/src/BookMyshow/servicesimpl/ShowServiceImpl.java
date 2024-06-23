package BookMyshow.servicesimpl;


import BookMyshow.entities.Seat;
import BookMyshow.entities.Show;
import BookMyshow.repositories.ShowRepository;
import BookMyshow.services.ShowService;

import java.util.List;

public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
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

