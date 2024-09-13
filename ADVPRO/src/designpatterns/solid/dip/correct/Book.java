package designpatterns.solid.dip.correct;


/*
    high-level modules should not depend upon low-level modules; they should depend on abstractions.
    Secondly, abstractions should not depend upon details; details should depend upon abstractions

    instead of high-level modules depending on low-level modules, both will depend on abstractions.
    Every dependency in the design should target an interface or an abstract class.
    No dependency should target a concrete class.
* */
public class Book implements Product {

    @Override
    public void seeReviews() { 
          
    }

    @Override
    public void getSample() {
          
    }
}
