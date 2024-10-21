package designpatterns.behavioral.command;

/*

    The Command Pattern encapsulates a request as an object, allowing for parameterization of clients
    with different requests, queuing of requests, and support for undoable operations.
    This pattern turns requests into stand-alone objects with their own behavior, facilitating the
    decoupling of senders and receivers.

    The Command Pattern is a behavioral design pattern.that converts a request into an
    object with all the required information such as what method to call, arguments, etc.

    It promotes loose coupling between the sender (client) and the receiver (invoker),
    allowing the client to request an operation without knowing the receiver's class or the
    method being called.

    Use the Command pattern when you need to support undo/redo functionality.
    Commands can encapsulate operations along with their parameters, making it possible
    to revert or replay actions.

    Use the Command pattern when you want to add new commands or operations to a system without
    modifying existing client code. New command classes can be added to introduce new behaviors.

    Use the Command pattern in transactional systems to encapsulate database operations as commands.
    This allows for easy rollback of transactions


*/
public class UberRidesDemo {
   public static void main(String[] args) {
       // Create a receiver
       RideService rideService = new RideService();

       // Create an invoker
       RideRequestInvoker rideRequestInvoker = new RideRequestInvoker();

       // Execute ride request and cancellation commands directly
       Command request1 = new RideRequestCommand(rideService, "Keerti", "Sarjapur", "Koramangala");
       Command request2 = new RideRequestCommand(rideService, "Amit", "Koramangala", "Indiranagar");
       Command cancel1 = new CancelRideCommand(rideService, "Keerti");

       // Process the ride requests and cancellations
       rideRequestInvoker.processRequest(request1);
       rideRequestInvoker.processRequest(request2);
       rideRequestInvoker.processRequest(cancel1);

       // Clean up (not required in Java)
   }
}
