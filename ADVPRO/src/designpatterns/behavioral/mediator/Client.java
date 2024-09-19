package designpatterns.behavioral.mediator;

/*
    We use the Mediator Design Pattern to provide a centralized communication medium between different
    objects in a system.
    Mediator pattern focuses on providing a mediator between objects for communication and help in
    implementing lose-coupling between objects. Objects ask the Mediator to interact on their behalf,
    rather than interacting directly with each other.

    The Mediator design pattern is very helpful in an enterprise application where multiple
    objects are interacting with each other. If the objects interact with each other directly,
    the system components become tightly-coupled with each other. They also make the maintainability
    cost higher and not flexible to extend easily.

    For example, the Air traffic controller is a great example of mediator pattern where the
    airport control room works as a mediator for communication between different flights.
    The mediator works as a router between objects and it can have its own logic to provide
    way of communication.

    Use the Mediator pattern in event-driven systems, where components need to react to events
    triggered by other components but shouldn't have direct dependencies on each other.

    In distributed systems, the Mediator pattern can be used to manage communication and
    coordination between distributed components or services, providing a centralized point for
    handling interactions.
*/
public class Client {
    public static void main(String[] args) {
        ChatRoomMediator chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        ((ChatRoom) chatRoom).addUser(alice);
        ((ChatRoom) chatRoom).addUser(bob);

        alice.sendMessage("Hi Bob!");
        bob.sendMessage("Hello Alice!");
    }
}
