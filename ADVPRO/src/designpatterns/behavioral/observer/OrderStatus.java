package designpatterns.behavioral.observer;

public class OrderStatus {

/*
    The Observer pattern is a behavioral design pattern that establishes a one-to-many dependency
    between objects so that when one object changes state, all its dependents (observers) are
    notified and updated automatically

    Objects that listen or watch for change are called observers and the object that is being
    watched is called a subject.

    We use Strategy Pattern when we have multiple algorithms for a specific task and client
    decides the actual implementation to be used at runtime.
*/
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
