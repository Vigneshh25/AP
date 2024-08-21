package designpatterns.solid.isp.correct;

public class OnlineCustomerImpl implements OrderInterface, PaymentInterface {
   
     @Override
     public void placeOrder() {

           // logic to place online order         
     }
   
     @Override
     public void payForOrder() {

           // logic to do online payment         
     }
}