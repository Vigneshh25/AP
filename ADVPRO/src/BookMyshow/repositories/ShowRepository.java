package BookMyshow.repositories;

import BookMyshow.entities.Show;

import java.util.List;

public interface ShowRepository {
    void addShow(Show show);
    Show getShowById(String showId);
    List<Show> getAllShows();
    void updateShow(Show show);
    void deleteShow(String showId);
}
