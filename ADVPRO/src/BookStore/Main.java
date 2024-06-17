package BookStore;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryBookRepository bookRepository = new InMemoryBookRepository();
        Order order = new Order();

        // Create users
        User customer = UserFactory.createUser("Customer", "Alice", "alice@example.com");
        User admin = UserFactory.createUser("Admin", "Bob", "bob@example.com");

        // Save users to repository
        userRepository.saveUser(customer);
        userRepository.saveUser(admin);

        // Display user info
        customer.displayInfo();
        admin.displayInfo();

        // Create books
        Book book1 = new Book.BookBuilder("Effective Java", "Joshua Bloch")
                .setPrice(45.99)
                .setISBN("978-0134685991")
                .build();
        Book book2 = new Book.BookBuilder("Clean Code", "Robert C. Martin")
                .setPrice(37.99)
                .setISBN("978-0132350884")
                .build();

        // Save books to repository
        bookRepository.saveBook(book1);
        bookRepository.saveBook(book2);

        // Display books
        System.out.println(book1);
        System.out.println(book2);

        // Add book to order
        order.addBook(book1);
        order.addBook(book2);

        // Add observer to order
        order.addObserver((OrderObserver) customer);

        // Set order status
        order.setStatus("Processing");
        order.setStatus("Shipped");

        // Payment using Strategy Pattern
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234567890123456");
        PaymentStrategy paypalPayment = new PayPalPayment("alice@example.com");

        // Pay for the order
        creditCardPayment.pay(order.getTotalPrice());
        paypalPayment.pay(order.getTotalPrice());

        // Apply discount using Decorator Pattern
//        Book discountedBook1 = new DiscountedBook(book1, 5.0);
//        System.out.println(discountedBook1.getTitle() + ": " + discountedBook1.getPrice());

        // Edge cases
        // Try adding a book with null details
        try {
            Book invalidBook = new Book.BookBuilder(null, null).build();
            bookRepository.saveBook(invalidBook);
        } catch (Exception e) {
            System.out.println("Error adding invalid book: " + e.getMessage());
        }

        // Try adding a user with null details
        try {
            User invalidUser = UserFactory.createUser(null, null, null);
            userRepository.saveUser(invalidUser);
        } catch (Exception e) {
            System.out.println("Error adding invalid user: " + e.getMessage());
        }
    }
}
