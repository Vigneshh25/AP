package BookMyshow.repositories;

import BookMyshow.entities.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    public Movie findById(String movieId) {
        return movies.get(movieId);
    }

    public List<Movie> search(String query) {
        return movies.values().stream().filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    public void save(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public List<Movie> getAll() {
        return new ArrayList<>(movies.values());
    }
}
