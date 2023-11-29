package LLD.IRCTC

public class PhonepeUPIPayment extends UPIPayment {
    @Override
    public void ProcessUPIPayment(double amount) {
        System.out.println("Using Phonepe UPI Payment");
    }
}
