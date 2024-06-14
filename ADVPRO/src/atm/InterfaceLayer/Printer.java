package atm.InterfaceLayer;

import atm.ModelLayer.Transaction;

public interface Printer {
    void printReceipt(Transaction transaction);
}
