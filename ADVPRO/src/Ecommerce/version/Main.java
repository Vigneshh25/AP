package Ecommerce.version;

// WebCrawlerWithSameHostnameMain.java
public class Main {
    public static void main(String[] args) {
        // User Management
        User user = new User("john_doe", "password123", "john@example.com");

        // Product Management
        Product product1 = new Product("Laptop", 1000.00, 10);
        Product product2 = new Product("Smartphone", 500.00, 20);

        // Shopping Cart
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product1);
        cart.addProduct(product2);

        System.out.println("Cart Total: " + cart.getTotalAmount());

        // Order Management
        Order order = new Order(user);
        order.addProduct(product1);
        order.addProduct(product2);

        System.out.println("Order Total: " + order.getTotalAmount());

        // Payment Processing
        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setPaymentStrategy(new CreditCardPayment());
        orderPayment.pay(order.getTotalAmount());

        orderPayment.setPaymentStrategy(new PayPalPayment());
        orderPayment.pay(order.getTotalAmount());

        // Observer Pattern for Stock Updates
        StockSubject stockSubject = new StockSubject();
        StockObserver stockObserver = new StockObserver();

        stockSubject.addObserver(stockObserver);
        stockSubject.setStockStatus("Laptop stock reduced to 9 + "+product1.getStock());
    }
}
