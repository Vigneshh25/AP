package designpatterns.behavioral.state;

public class Ringing implements MobileState {

      @Override
      public void getState() {
         System.out.println("Mobile is in ringing state");
      }
}