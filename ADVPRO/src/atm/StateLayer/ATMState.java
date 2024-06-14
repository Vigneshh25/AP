package atm.StateLayer;

import atm.ModelLayer.TransactionType;

public interface ATMState {
    void insertCard();
    void removeCard();
    void enterPIN(String pin);
    void selectOperation(TransactionType transactionType);
    void checkBalance();
    void withdrawCash(double amount);
}
