package designpatterns.behavioral.observer;

public class OrderStatus {
   public static void main(String[] args) {
       // Create an order
       Order order1 = new Order(123);

       // Create customers, restaurants, drivers, and a call center to track the order
       Customer customer1 = new Customer("Customer 1");
       Restaurant restaurant1 = new Restaurant("Rest 1");
       DeliveryDriver driver1 = new DeliveryDriver("Driver 1");
       CallCenter callCenter = new CallCenter();

       // Attach observers to the order
       order1.attach(customer1);
       order1.attach(restaurant1);
       order1.attach(driver1);
       order1.attach(callCenter);

       // Simulate order status updates
       order1.setStatus("Out for Delivery");

       // Detach an observer (if needed)
       order1.detach(callCenter);

       // Simulate more order status updates
       order1.setStatus("Delivered");
   }
}
