package BookStore;

import BookStore.controller.BookStoreController;
import BookStore.factory.UserFactory;
import BookStore.model.Order;
import BookStore.model.User;
import BookStore.repository.BookRepository;
import BookStore.repository.UserRepository;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        BookRepository bookRepository = new BookRepository();
        Order order = new Order();

        BookStoreController controller = new BookStoreController(userRepository,bookRepository,order);

        // Create users
        System.out.println(controller.createUser("Customer", "Alice", "alice@example.com"));
        System.out.println(controller.createUser("Admin", "Bob", "bob@example.com"));

        // Display user info
        controller.displayUserInfo("alice@example.com");
        controller.displayUserInfo("bob@example.com");

        // Add books to the repository
        System.out.println(controller.addBook("Effective Java", "Joshua Bloch", 45.99, "978-0134685991"));
        System.out.println(controller.addBook("Clean Code", "Robert C. Martin", 37.99, "978-0132350884"));

        // Display all books
        controller.displayBooks();

        // Add books to the order
        System.out.println(controller.addBookToOrder("978-0134685991"));
        System.out.println(controller.addBookToOrder("978-0132350884"));

        // Add observer to the order
        User alice = UserFactory.createUser("Customer", "Alice", "alice@example.com");
        controller.addOrderObserver(alice);

        // Set order status
        controller.setOrderStatus("Processing");
        controller.setOrderStatus("Shipped");

        // Process payment
        System.out.println(controller.processPayment("CreditCard", 83.98));
        System.out.println(controller.processPayment("PayPal", 83.98));
    }
}

