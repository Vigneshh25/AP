package Ecommerce;

import Ecommerce.Entities.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EcommerceFacade facade = new EcommerceFacade();

        // Test Case 1: User Registration
        System.out.println("Test Case 1: User Registration");
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        facade.registerUser(user);
        System.out.println("User registered: " + user.getName());

        // Test Case 2: User Login
        System.out.println("Test Case 2: User Login");
        User loggedInUser = facade.loginUser("john@example.com", "password");
        if (loggedInUser != null) {
            System.out.println("User logged in: " + loggedInUser.getName());
        } else {
            System.out.println("User login failed.");
        }

        // Test Case 3: Add Products
        System.out.println("Test Case 3: Add Products");
        Product product1 = new Product();
        product1.setId("101");
        product1.setName("Laptop");
        product1.setDescription("Gaming Laptop");
        product1.setPrice(1500.0);
        facade.addProduct(product1);
        System.out.println("Product added: " + product1.getName());

        Product product2 = new Product();
        product2.setId("102");
        product2.setName("Mouse");
        product2.setDescription("Wireless Mouse");
        product2.setPrice(50.0);
        facade.addProduct(product2);
        System.out.println("Product added: " + product2.getName());

        // Test Case 4: Add Stock to Inventory
        System.out.println("Test Case 4: Add Stock to Inventory");
        facade.addStock(product1.getId(), 10);
        facade.addStock(product2.getId(), 20);
        System.out.println("Stock added for products.");

        // Test Case 5: Add Items to Cart
        System.out.println("Test Case 5: Add Items to Cart");
        facade.addItemToCart(user.getId(), product1.getId(), 2);
        facade.addItemToCart(user.getId(), product2.getId(), 5);
        Cart userCart = facade.getCartByUserId(user.getId());
        System.out.println("Items added to cart: " + userCart.getItems());

        // Test Case 6: Place Order
        System.out.println("Test Case 6: Place Order");
        facade.placeOrder(user);

        // Test Case 7: Place Multiple Orders Concurrently
        System.out.println("Test Case 7: Place Multiple Orders Concurrently");
        facade.placeMultipleOrdersConcurrently(user, 3);

        // Test Case 8: Retrieve Orders for User
        System.out.println("Test Case 8: Retrieve Orders for User");
        for (Order order : facade.getOrdersByUserId(user.getId())) {
            System.out.println("Order ID: " + order.getOrderId() + ", Total Amount: " + order.getTotalAmount());
        }

        // Test Case 9: Check Stock Levels
        System.out.println("Test Case 9: Check Stock Levels");
        System.out.println("Stock for product1 (Laptop): " + facade.getStock(product1.getId()));
        System.out.println("Stock for product2 (Mouse): " + facade.getStock(product2.getId()));

        // Test Case 10: Search Products
        System.out.println("Test Case 10: Search Products");
        List<Product> searchResults = facade.searchProducts("laptop");
        System.out.println("Search results for 'laptop':");
        searchResults.forEach(p -> System.out.println(p.getName()));

        // Test Case 11: Add Review
        System.out.println("Test Case 11: Add Review");
        Review review = new Review();
        review.setUserId(user.getId());
        review.setProductId(product1.getId());
        review.setReviewText("Excellent gaming laptop!");
        review.setRating(5);
        facade.addReview(review);
        System.out.println("Review added for product: " + product1.getName());

        // Test Case 12: Get Reviews for Product
        System.out.println("Test Case 12: Get Reviews for Product");
        List<Review> reviews = facade.getReviewsByProductId(product1.getId());
        System.out.println("Reviews for product " + product1.getName() + ":");
        reviews.forEach(r -> System.out.println(r.getReviewText() + " - Rating: " + r.getRating()));

        // Test Case 13: Add to Wishlist
        System.out.println("Test Case 13: Add to Wishlist");
        facade.addToWishlist(user.getId(), product1.getId());
        facade.addToWishlist(user.getId(), product2.getId());
        List<String> wishlist = facade.getWishlist(user.getId());
        System.out.println("Wishlist for user " + user.getName() + ": " + wishlist);

        // Test Case 14: Remove from Wishlist
        // Test Case 14: Remove from Wishlist (continued)
        System.out.println("Test Case 14: Remove from Wishlist");
        facade.removeFromWishlist(user.getId(), product2.getId());
        wishlist = facade.getWishlist(user.getId());
        System.out.println("Updated Wishlist for user " + user.getName() + ": " + wishlist);

        // Test Case 15: Apply Discount
        System.out.println("Test Case 15: Apply Discount");
        Discount discount = new Discount();
        discount.setCode("DISCOUNT10");
        discount.setPercentage(10.0);
        facade.applyDiscount(discount);

        double originalPrice = product1.getPrice();
        double discountedPrice = originalPrice - (originalPrice * discount.getPercentage() / 100);
        System.out.println("Original price: " + originalPrice);
        System.out.println("Discounted price: " + discountedPrice);

        // Test Case 16: Get Recommendations
        System.out.println("Test Case 16: Get Recommendations");
        facade.addRecommendation(user.getId(), Arrays.asList(product1.getId(), product2.getId()));
        List<Product> recommendedProducts = facade.getRecommendations(user.getId(), facade.getProductService());
        System.out.println("Recommended products for user " + user.getName() + ": " + recommendedProducts);

        // Shutdown the executor service
        facade.shutdownExecutorService();
    }
}
