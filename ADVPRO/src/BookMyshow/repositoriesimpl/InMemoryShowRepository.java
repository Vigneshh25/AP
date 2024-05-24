package BookMyshow.repositoriesimpl;

import BookMyshow.entities.Show;
import BookMyshow.repositories.ShowRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryShowRepository implements ShowRepository {
    private Map<String, Show> shows = new HashMap<>();

    @Override
    public Show findById(String showId) {
        return shows.get(showId);
    }

    // Method to add shows for testing purposes
    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }
}
