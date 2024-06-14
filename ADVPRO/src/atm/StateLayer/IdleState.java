package atm.StateLayer;

import atm.ATM;
import atm.ModelLayer.TransactionType;

public class IdleState implements ATMState {
    private ATM atm;

    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        String cardNumber = atm.getCardReader().readCard();
        atm.setCurrentCardNumber(cardNumber);
        atm.getScreen().displayMessage("Card inserted. Please enter your PIN.");
        atm.setCurrentState(new CardInsertedState(atm));
    }

    @Override
    public void removeCard() {
        atm.getScreen().displayMessage("No card to remove.");
    }

    @Override
    public void enterPIN(String pin) {
        atm.getScreen().displayMessage("No card inserted.");
    }

    @Override
    public void selectOperation(TransactionType transactionType) {
        atm.getScreen().displayMessage("No card inserted.");
    }

    @Override
    public void checkBalance() {
        atm.getScreen().displayMessage("No card inserted.");
    }

    @Override
    public void withdrawCash(double amount) {
        atm.getScreen().displayMessage("No card inserted.");
    }
}
