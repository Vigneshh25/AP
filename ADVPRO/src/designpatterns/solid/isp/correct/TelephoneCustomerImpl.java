package designpatterns.solid.isp.correct;

public class TelephoneCustomerImpl implements OrderInterface, PaymentInterface {
 
     @Override
     public void placeOrder() {

           // logic to place telephonic order         
     }
    
     @Override
     public void payForOrder() {

           // logic to do online payment         
     }
}