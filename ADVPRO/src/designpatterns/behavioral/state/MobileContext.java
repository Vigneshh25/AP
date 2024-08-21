package designpatterns.behavioral.state;

public class MobileContext implements MobileState {

      private MobileState mobileState;

      public void setMobileState(MobileState state) {
         mobileState=state;
      }

      public MobileContext(MobileState mobileState){
         this.mobileState= mobileState;
      }

      public void getState(){
         mobileState.getState();
      }
}