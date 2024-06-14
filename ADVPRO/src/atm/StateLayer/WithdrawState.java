package atm.StateLayer;

import atm.ATM;
import atm.ModelLayer.TransactionType;

public class WithdrawState implements ATMState {
    private ATM atm;

    public WithdrawState(ATM atm) {
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
        atm.getScreen().displayMessage("Operation in progress.");
    }

    @Override
    public void withdrawCash(double amount) {
        String cardNumber = atm.getCurrentCardNumber();
        atm.getAccountService().withdraw(cardNumber, amount);
        atm.getCashDispenser().dispense((int) amount);  // Assuming dispenseCash takes an int amount
        atm.getScreen().displayMessage("Withdrawal successful.");
        atm.setCurrentState(new AuthenticatedState(atm));
    }
}
