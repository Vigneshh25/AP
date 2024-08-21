package designpatterns.solid.isp.correct;

public class WalkInCustomerImpl implements OrderInterface, PaymentInterface {
    
     @Override
     public void placeOrder() {

           // logic to place in-person order         
     }
    
     @Override
     public void payForOrder() {

           // logic to do in-person payment        
     }
}