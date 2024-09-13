package designpatterns.behavioral.mediator;

public class MediatorPatternTest {

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
*/
      public static void main(String[] args) {
         IMediator mediator = new ConcreteMediator();

         ColleagueA talkColleague= new ColleagueA(mediator);
         talkColleague.doSomething();

         ColleagueB fightColleague = new ColleagueB(mediator);
         fightColleague.doSomething();
      }
}