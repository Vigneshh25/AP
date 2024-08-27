package BookMyshow;

import BookMyshow.entities.Booking;
import BookMyshow.entities.Movie;
import BookMyshow.entities.Seat;
import BookMyshow.entities.User;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Scanner for user input.txt
        Scanner scanner = new Scanner(System.in);

        User currentUser = null;
        BookingController bookingController = new BookingController();

        while (true) {
            displayMainMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    currentUser = registerUser(bookingController, scanner);
                    break;
                case 2:
                    currentUser = loginUser(bookingController, scanner);
                    if (currentUser != null) {
                        handleUserActions(currentUser, bookingController, scanner);
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

    private static User registerUser(BookingController bookingController, Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.next();
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        User user = new User(UUID.randomUUID().toString(), name, email, password);
        if (bookingController.registerUser(user)) {
            System.out.println("User registered successfully.");
            return user;
        } else {
            System.out.println("Registration failed. Email may already be in use.");
            return null;
        }
    }

    private static User loginUser(BookingController bookingController, Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        if (bookingController.loginUser(email, password)) {
            System.out.println("Login successful.");
            return bookingController.getUserByEmail(email);
        } else {
            System.out.println("Login failed. Please check your email and password.");
            return null;
        }
    }

    private static void handleUserActions(User currentUser, BookingController bookingController, Scanner scanner) {
        while (true) {
            displayUserMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1:
                    viewEvents(bookingController);
                case 2:
                    bookTickets(currentUser, bookingController, scanner);
                    break;
                case 3:
                    viewBookings(currentUser, bookingController);
                    break;
                case 4:
                    updateProfile(currentUser, bookingController, scanner);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewEvents(BookingController bookingController) {
        bookingController.getAllMovies().forEach(System.out::println);
    }

    private static void displayUserMenu() {
        System.out.println("1. View Events");
        System.out.println("2. Book Tickets");
        System.out.println("3. View Bookings");
        System.out.println("4. Update Profile");
        System.out.println("5. Logout");
    }

    private static void bookTickets(User currentUser, BookingController bookingController, Scanner scanner) {
        System.out.println("Enter show ID:");
        String showId = scanner.next();
        List<Seat> seats = new ArrayList<>(); // Add seat selection logic here

        try {
            Booking booking = bookingController.createBooking(showId, currentUser.getUserId(), seats);
            System.out.println("Booking created with ID: " + booking.getBookingId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewBookings(User currentUser, BookingController bookingController) {
        System.out.println("Viewing bookings for user: " + currentUser.getName());
        List<Booking> bookings = bookingController.getUserBookings(currentUser.getUserId());
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private static void updateProfile(User currentUser, BookingController bookingController, Scanner scanner) {
        System.out.println("Enter new name:");
        String newName = scanner.next();
        currentUser.setName(newName);
        bookingController.updateUserProfile(currentUser);
        System.out.println("Profile updated.");
    }
}
