package BookMyshow.repositoriesimpl;

import BookMyshow.entities.Movie;
import BookMyshow.repositories.MovieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryMovieRepository implements MovieRepository {
    private Map<String, Movie> movies = new HashMap<>();

    @Override
    public Movie findById(String movieId) {
        return movies.get(movieId);
    }

    @Override
    public List<Movie> search(String query) {
        return movies.values().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
