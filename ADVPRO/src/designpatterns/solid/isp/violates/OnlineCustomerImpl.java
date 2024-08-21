package designpatterns.solid.isp.violates;

public class OnlineCustomerImpl implements RestaurantInterface {

     public void acceptOnlineOrder() {

           //logic for placing online order
     }

     public void acceptTelephoneOrder() {

           //Not Applicable for Online Order

           throw new UnsupportedOperationException();
     }

     public void payOnline() {

           //logic for paying online
     }

     public void acceptWalkInCustomerOrder() {

           //Not Applicable for Online Order

           throw new UnsupportedOperationException();
     }

     public void payInPerson() {

           //Not Applicable for Online Order

           throw new UnsupportedOperationException();
     }
}