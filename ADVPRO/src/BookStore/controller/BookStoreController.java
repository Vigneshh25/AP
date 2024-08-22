package BookStore.controller;


import BookStore.factory.UserFactory;
import BookStore.model.*;
import BookStore.repository.*;
import BookStore.service.CreditCardPayment;
import BookStore.service.*;

import java.util.Collections;
import java.util.List;

public class BookStoreController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final Order order;

    public BookStoreController(UserRepository userRepository, BookRepository bookRepository, Order order) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.order = order;
    }

    // Create a new user
    public String createUser(String role, String name, String email) {
        try {
            User user = UserFactory.createUser(role, name, email);
            userRepository.saveUser(user);
            return "User created successfully: " + name;
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    // Display user info
    public void displayUserInfo(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            user.displayInfo();
        } else {
            System.out.println("User not found.");
        }
    }

    // Add a book to the repository
    public String addBook(String title, String author, double price, String isbn) {
        try {
            Book book = new Book.BookBuilder(title, author)
                    .setPrice(price)
                    .setISBN(isbn)
                    .build();
            bookRepository.saveBook(book);
            return "Book added successfully: " + title;
        } catch (Exception e) {
            return "Error adding book: " + e.getMessage();
        }
    }

    // Display all books
    public void displayBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("No books available.");
        }
    }

    // Add a book to an order
    public String addBookToOrder(String isbn) {
        Book book = bookRepository.findBookByISBN(isbn);
        if (book != null) {
            order.addBook(book);
            return "Book added to order: " + book.getTitle();
        } else {
            return "Book not found with ISBN: " + isbn;
        }
    }

    // Set order status and notify observers
    public void setOrderStatus(String status) {
        order.setStatus(status);
    }

    // Add observer to order
    public void addOrderObserver(User user) {
        order.addObserver((OrderObserver) user);
    }

    // Process payment for the order
    public String processPayment(String paymentType, double amount) {
        PaymentStrategy paymentStrategy;
        if ("CreditCard".equalsIgnoreCase(paymentType)) {
            paymentStrategy = new CreditCardPayment("1234567890123456");
        } else if ("PayPal".equalsIgnoreCase(paymentType)) {
            paymentStrategy = new PayPalPayment("user@example.com");
        } else {
            return "Invalid payment type.";
        }

        paymentStrategy.pay(amount);
        return "Payment processed using " + paymentType;
    }
}
