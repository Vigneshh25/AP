package BookMyshow.repositoriesimpl;

import BookMyshow.entities.Show;
import BookMyshow.repositories.ShowRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryShowRepository implements ShowRepository {
    private Map<String, Show> shows = new HashMap<>();

    @Override
    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }

    @Override
    public Show getShowById(String showId) {
        return shows.get(showId);
    }

    @Override
    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }

    @Override
    public void updateShow(Show show) {
        if (shows.containsKey(show.getShowId())) {
            shows.put(show.getShowId(), show);
        } else {
            throw new IllegalArgumentException("Show not found");
        }
    }

    @Override
    public void deleteShow(String showId) {
        shows.remove(showId);
    }
}

