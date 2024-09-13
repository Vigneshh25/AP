package designpatterns.behavioral.state;

public class StatePatternTest {

/*
   We use State Design Pattern when an Object changes its behavior on change of its internal state.
   We can define the state of an object as its exact condition at any given point of time,
   depending on the values of its properties or attributes. The set of methods implemented by a class
   constitutes the behavior of its instances. Whenever there is a change in the values of its
   attributes, we say that the state of an object has changed
*/

      public static void main(String[] args) {
         MobileContext ctx = new MobileContext(new Ringing());
         ctx.getState();
         ctx.getState();
         ctx.setMobileState(new Vibration());
         ctx.getState();
         ctx.getState();
         ctx.getState();
         ctx.setMobileState(new Silent());
         ctx.getState();
         ctx.getState();
      }
}