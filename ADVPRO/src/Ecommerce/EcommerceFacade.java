package Ecommerce;


import Ecommerce.Entities.*;
import Ecommerce.Services.*;
import Ecommerce.Strategies.CreditCardPaymentStrategy;

import java.util.List;

public class EcommerceFacade {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final ThreadPoolExecutorService executorService;
    private final PaymentService paymentService;
    private final SearchService searchService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;
    private final DiscountService discountService;
    private final RecommendationService recommendationService;

    public EcommerceFacade() {
        this.userService = UserService.getInstance();
        this.productService = ProductService.getInstance();
        this.cartService = CartService.getInstance();
        this.orderService = OrderService.getInstance();
        this.inventoryService = InventoryService.getInstance();
        this.executorService = ThreadPoolExecutorService.getInstance();
        this.paymentService = new PaymentService();
        this.searchService = SearchService.getInstance();
        this.reviewService = ReviewService.getInstance();
        this.wishlistService = WishlistService.getInstance();
        this.discountService = DiscountService.getInstance();
        this.recommendationService = RecommendationService.getInstance();
    }

    public void registerUser(User user) {
        userService.registerUser(user);
    }

    public User loginUser(String email, String password) {
        return userService.loginUser(email, password);
    }

    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    public void addStock(String productId, int quantity) {
        inventoryService.addStock(productId, quantity);
    }

    public void addItemToCart(String userId, String productId, int quantity) {
        cartService.addItemToCart(userId, productId, quantity);
    }

    public Cart getCartByUserId(String userId) {
        return cartService.getCartByUserId(userId);
    }

    public ProductService getProductService() {
        return productService;
    }

    public void placeOrder(User user) {
        Runnable placeOrderTask = () -> {
            Cart cart = cartService.getCartByUserId(user.getId());
            Order order = new Order();
            order.setOrderId("501");
            order.setUserId(user.getId());
            order.setItems(cart.getItems());
            order.setTotalAmount(cart.getItems().entrySet().stream().mapToDouble(entry -> productService.getProductById(entry.getKey()).getPrice() * entry.getValue()).sum());

            boolean success = orderService.placeOrder(order);
            if (success) {
                // Process payment
                Payment payment = new Payment();
                payment.setUserId(user.getId());
                payment.setAmount(order.getTotalAmount());
                paymentService.setPaymentStrategy(new CreditCardPaymentStrategy());
                paymentService.processPayment(payment);

                // Clear the cart after successful order
                cartService.getCartByUserId(user.getId()).getItems().clear();
            }
        };
        executorService.execute(placeOrderTask);
    }

    public void placeMultipleOrdersConcurrently(User user, int orderCount) {
        for (int i = 0; i < orderCount; i++) {
            placeOrder(user);
        }
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderService.getOrdersByUserId(userId);
    }

    public int getStock(String productId) {
        return inventoryService.getStock(productId);
    }

    public List<Product> searchProducts(String query) {
        return searchService.searchProducts(query);
    }

    public void addReview(Review review) {
        reviewService.addReview(review);
    }

    public List<Review> getReviewsByProductId(String productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    public void addToWishlist(String userId, String productId) {
        wishlistService.addToWishlist(userId, productId);
    }

    public void removeFromWishlist(String userId, String productId) {
        wishlistService.removeFromWishlist(userId, productId);
    }

    public List<String> getWishlist(String userId) {
        return wishlistService.getWishlist(userId);
    }

    public void applyDiscount(Discount discount) {
        discountService.addDiscount(discount);
    }

    public List<Product> getRecommendations(String userId, ProductService productService) {
        return recommendationService.getRecommendations(userId, productService);
    }

    public void addRecommendation(String userId, List<String> productIds) {
        recommendationService.addRecommendation(userId, productIds);
    }

    public List<Product> getRecommendations(String userId) {
        return recommendationService.getRecommendations(userId, productService);
    }

    public void shutdownExecutorService() {
        executorService.shutdown();
    }

}


