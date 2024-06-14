package atm.ServiceLayer;

import atm.InterfaceLayer.Printer;
import atm.ModelLayer.Transaction;

public class PrinterImpl implements Printer {
    @Override
    public void printReceipt(Transaction transaction) {
        // Simulate printing receipt
        System.out.println("Printing receipt for transaction: " + transaction);
    }
}
