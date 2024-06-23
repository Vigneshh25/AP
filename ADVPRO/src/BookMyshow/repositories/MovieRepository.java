package BookMyshow.repositories;

import BookMyshow.entities.Movie;

import java.util.List;

public interface MovieRepository {
    Movie findById(String movieId);
    List<Movie> search(String query);
    void save(Movie movie);
}
