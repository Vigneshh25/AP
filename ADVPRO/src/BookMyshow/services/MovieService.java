package BookMyshow.services;

import BookMyshow.entities.Movie;

import java.util.List;

public interface MovieService {
    Movie getDetails(String movieId);
    List<Movie> searchMovies(String query);
}
