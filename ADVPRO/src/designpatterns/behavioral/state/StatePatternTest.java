package designpatterns.behavioral.state;

public class   StatePatternTest {

/*
   The State design pattern is used to allow an object to change its behavior when its internal state
   changes. This pattern is particularly useful when an object's behavior depends on its state, and the
   state can change during runtime based on certain conditions or events. The State pattern encapsulates
   each state as an object and allows the object to transition from one state to another by changing its
   current state object.

   Use the State pattern when an object's behavior needs to change dynamically at runtime based on
   changes in its state.
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