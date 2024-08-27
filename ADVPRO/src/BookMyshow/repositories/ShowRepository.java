package BookMyshow.repositories;

import BookMyshow.entities.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRepository {
    private final Map<String, Show> shows = new HashMap<>();

    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }

    public Show getShowById(String showId) {
        return shows.get(showId);
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }

    public void updateShow(Show show) {
        if (shows.containsKey(show.getShowId())) {
            shows.put(show.getShowId(), show);
        } else {
            throw new IllegalArgumentException("Show not found");
        }
    }

    public void deleteShow(String showId) {
        shows.remove(showId);
    }
}

