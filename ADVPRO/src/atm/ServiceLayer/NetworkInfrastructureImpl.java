package atm.ServiceLayer;

import atm.InterfaceLayer.NetworkInfrastructure;
import atm.ModelLayer.Transaction;

public class NetworkInfrastructureImpl implements NetworkInfrastructure {
    @Override
    public void sendTransaction(Transaction transaction) {
        // Simulate sending transaction to the bank
        System.out.println("Transaction sent to the bank: " + transaction);
    }
}
