package BookMyshow;

import BookMyshow.entities.*;
import BookMyshow.gateways.PaymentGateway;
import BookMyshow.gateways.StripePaymentGateway;
import BookMyshow.repositories.*;
import BookMyshow.services.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingController {
    private final BookingService bookingService;
    private final MovieService movieService;
    private final UserService userService;
    private final ShowService showService;

    public BookingController() {
        // Repositories
        UserRepository userRepository = new UserRepository();
        ShowRepository showRepository = new ShowRepository();
        BookingRepository bookingRepository = new BookingRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        MovieRepository movieRepository = new MovieRepository();

        // Services
        PaymentGateway paymentGateway = new StripePaymentGateway();
        PaymentService paymentService = new PaymentService(paymentGateway, paymentRepository);
        ShowService showService = new ShowService(showRepository);
        this.bookingService = new BookingService(bookingRepository, paymentService, showService);
        this.movieService = new MovieService(movieRepository);
        this.userService = new UserService(userRepository);
        this.showService = showService;


        // Add movies
        Movie movie1 = new Movie("m1", "Movie One", "Action", 120);
        Movie movie2 = new Movie("m2", "Movie Two", "Comedy", 90);
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        // Create seats and show
        Seat seat1 = new Seat("s1", "screen1", 1, 1, SeatType.REGULAR);
        Seat seat2 = new Seat("s2", "screen1", 1, 2, SeatType.PREMIUM);
        Seat seat3 = new Seat("s3", "screen1", 1, 3, SeatType.REGULAR);
        Map<Seat, Boolean> seatAvailability = new HashMap<>();
        seatAvailability.put(seat1, true);
        seatAvailability.put(seat2, true);
        seatAvailability.put(seat3, true);

        Show show1 = new Show("show1", "m1", "theater1", "screen1", LocalDateTime.now().plusDays(1), seatAvailability);
        showRepository.addShow(show1);
    }

    public Booking createBooking(String showId, String userId, List<Seat> seats) {
        return bookingService.createBooking(showId, userId, seats);
    }

    public boolean cancelBooking(String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    public Booking getBookingDetails(String bookingId) {
        return bookingService.getBookingDetails(bookingId);
    }

    public Movie getMovieDetails(String movieId) {
        return movieService.getDetails(movieId);
    }

    public List<Movie> searchMovies(String query) {
        return movieService.searchMovies(query);
    }

    public boolean registerUser(User user) {
        return userService.register(user);
    }

    public List<Booking> getUserBookings(String userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    public boolean loginUser(String email, String password) {
        return userService.login(email, password);
    }

    public void updateUserProfile(User user) {
        userService.updateProfile(user);
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public boolean checkSeatAvailability(String showId, List<Seat> seats) {
        return showService.checkAvailability(showId, seats);
    }
    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }
}
