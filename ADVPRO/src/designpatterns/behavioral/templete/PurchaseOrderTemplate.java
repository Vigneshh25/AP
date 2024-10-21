package designpatterns.behavioral.templete;

public abstract class PurchaseOrderTemplate {

      public boolean isGift;
      public abstract void doSelect();
      public abstract void doPayment();
      public final void doPack(){
         System.out.println("Gift wrap done.");
      }
      public abstract void doDelivery();
      public final void processOrder() {
         doSelect();
         doPayment();
         // if(isGift){
           doPack();
         // }
         doDelivery();
      }
}