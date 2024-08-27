package BookMyshow.services;

import BookMyshow.entities.Movie;
import BookMyshow.repositories.MovieRepository;

import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getDetails(String movieId) {
        return movieRepository.findById(movieId);
    }

    public List<Movie> searchMovies(String query) {
        return movieRepository.search(query);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }
}
