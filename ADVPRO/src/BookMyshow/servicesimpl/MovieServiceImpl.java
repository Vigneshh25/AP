package BookMyshow.servicesimpl;

import BookMyshow.entities.Movie;
import BookMyshow.repositories.MovieRepository;
import BookMyshow.services.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getDetails(String movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public List<Movie> searchMovies(String query) {
        return movieRepository.search(query);
    }
}
