package atm.StateLayer;

import atm.ATM;
import atm.ModelLayer.TransactionType;

public class CheckBalanceState implements ATMState {
    private ATM atm;

    public CheckBalanceState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        atm.getScreen().displayMessage("Operation in progress.");
    }

    @Override
    public void removeCard() {
        atm.getScreen().displayMessage("Operation in progress.");
    }

    @Override
    public void enterPIN(String pin) {
        atm.getScreen().displayMessage("Operation in progress.");
    }

    @Override
    public void selectOperation(TransactionType transactionType) {
        atm.getScreen().displayMessage("Operation in progress.");
    }

    @Override
    public void checkBalance() {
        String cardNumber = atm.getCurrentCardNumber();
        double balance = atm.getAccountService().checkBalance(cardNumber);
        atm.getScreen().displayMessage("Balance: " + balance);
        atm.setCurrentState(new AuthenticatedState(atm));
    }

    @Override
    public void withdrawCash(double amount) {
        atm.getScreen().displayMessage("Operation in progress.");
    }
}
