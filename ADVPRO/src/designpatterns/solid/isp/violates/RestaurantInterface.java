package designpatterns.solid.isp.violates;

public interface RestaurantInterface {
    
     public void acceptOnlineOrder();

     public void acceptTelephoneOrder();

     public void acceptWalkInCustomerOrder();

     public void payOnline();

     public void payInPerson();

}