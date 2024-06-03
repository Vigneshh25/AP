package BookMyshow;

import BookMyshow.entities.*;
import BookMyshow.gateways.PaymentGateway;
import BookMyshow.gateways.StripePaymentGateway;
import BookMyshow.repositories.*;
import BookMyshow.repositoriesimpl.*;
import BookMyshow.services.*;
import BookMyshow.servicesimpl.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Repositories
        UserRepository userRepository = new InMemoryUserRepository();
        ShowRepository showRepository = new InMemoryShowRepository();
        BookingRepository bookingRepository = new InMemoryBookingRepository();
        PaymentRepository paymentRepository = new InMemoryPaymentRepository();
        MovieRepository movieRepository = new InMemoryMovieRepository();

        // Services
        PaymentGateway paymentGateway = new StripePaymentGateway();
        PaymentService paymentService = new PaymentServiceImpl(paymentGateway, paymentRepository);
        ShowService showService = new ShowServiceImpl(showRepository);
        BookingService bookingService = new BookingServiceImpl(bookingRepository, paymentService, showService);
        UserService userService = new UserServiceImpl(userRepository);
        MovieService movieService = new MovieServiceImpl(movieRepository);

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        User currentUser = null;

        while (true) {
            displayMainMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    currentUser = registerUser(userService, scanner);
                    break;
                case 2:
                    currentUser = loginUser(userService, scanner);
                    if (currentUser != null) {
                        handleUserActions(currentUser, bookingService, userService, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private static int getUserChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static User registerUser(UserService userService, Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.next();
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        User user = new User(UUID.randomUUID().toString(), name, email, password);
        if (userService.register(user)) {
            System.out.println("User registered successfully.");
            return user;
        } else {
            System.out.println("Registration failed. Email may already be in use.");
            return null;
        }
    }

    private static User loginUser(UserService userService, Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        if (userService.login(email, password)) {
            System.out.println("Login successful.");
            return userService.getUserByEmail(email);
        } else {
            System.out.println("Login failed. Please check your email and password.");
            return null;
        }
    }

    private static void handleUserActions(User currentUser, BookingService bookingService, UserService userService, Scanner scanner) {
        while (true) {
            displayUserMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    bookTickets(currentUser, bookingService, scanner);
                    break;
                case 2:
                    viewBookings(currentUser, bookingService);
                    break;
                case 3:
                    updateProfile(currentUser, userService, scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayUserMenu() {
        System.out.println("1. Book Tickets");
        System.out.println("2. View Bookings");
        System.out.println("3. Update Profile");
        System.out.println("4. Logout");
    }

    private static void bookTickets(User currentUser, BookingService bookingService, Scanner scanner) {
        System.out.println("Enter show ID:");
        String showId = scanner.next();
        List<Seat> seats = new ArrayList<>(); // Add seat selection logic here

        try {
            Booking booking = bookingService.createBooking(showId, currentUser.getUserId(), seats);
            System.out.println("Booking created with ID: " + booking.getBookingId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewBookings(User currentUser, BookingService bookingService) {
        System.out.println("Viewing bookings...");
        // Fetch and display bookings for the current user
        // Example: bookingService.getBookingsByUserId(currentUser.getUserId());
    }

    private static void updateProfile(User currentUser, UserService userService, Scanner scanner) {
        System.out.println("Enter new name:");
        String newName = scanner.next();
        currentUser.setName(newName);
        userService.updateProfile(currentUser);
        System.out.println("Profile updated.");
    }
}
