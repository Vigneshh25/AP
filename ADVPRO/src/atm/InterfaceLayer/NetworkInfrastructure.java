package atm.InterfaceLayer;

import atm.ModelLayer.Transaction;

public interface NetworkInfrastructure {
    void sendTransaction(Transaction transaction);
}
