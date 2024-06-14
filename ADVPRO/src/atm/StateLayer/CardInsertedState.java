package atm.StateLayer;

import atm.ATM;
import atm.ModelLayer.TransactionType;

public class CardInsertedState implements ATMState {
    private ATM atm;

    public CardInsertedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        atm.getScreen().displayMessage("Card already inserted.");
    }

    @Override
    public void removeCard() {
        atm.getScreen().displayMessage("Card removed.");
        atm.setCurrentCardNumber(null);
        atm.setCurrentState(new IdleState(atm));
    }

    @Override
    public void enterPIN(String pin) {
        // Simulate PIN authentication
        atm.getScreen().displayMessage("PIN entered. Please select an operation.");
        atm.setCurrentState(new AuthenticatedState(atm));
    }

    @Override
    public void selectOperation(TransactionType transactionType) {
        atm.getScreen().displayMessage("Enter PIN first.");
    }

    @Override
    public void checkBalance() {
        atm.getScreen().displayMessage("Enter PIN first.");
    }

    @Override
    public void withdrawCash(double amount) {
        atm.getScreen().displayMessage("Enter PIN first.");
    }
}
