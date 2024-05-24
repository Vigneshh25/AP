package BookMyshow.repositories;

import BookMyshow.entities.Show;

public interface ShowRepository {
    Show findById(String showId);
}
