package designpatterns.behavioral.templete;

public class StorePurchaseOrder extends PurchaseOrderTemplate {

      @Override
      public void doSelect() {
         System.out.println("Customer chooses the item from self.");
      }

      @Override
      public void doPayment() {
         System.out.println("Pays at the counter through cash/card");
      }

      @Override
      public void doDelivery() {
         System.out.println("Item deliverd to in-delivery counter.");
      }
}