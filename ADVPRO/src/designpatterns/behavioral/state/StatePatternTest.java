package designpatterns.behavioral.state;

public class StatePatternTest {

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