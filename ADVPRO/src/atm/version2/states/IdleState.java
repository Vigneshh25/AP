package atm.version2.states;

import atm.version2.hardware.ATM;

public class IdleState implements ATMState {
    private ATM atm;

    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        String cardNumber = atm.getCardReader().readCard();
        atm.setCurrentCardNumber(cardNumber);
        atm.setState(atm.getCardInsertedState());
        atm.getScreen().displayMessage("Card inserted. Please enter your PIN.");
    }

    @Override
    public void ejectCard() {
        atm.getScreen().displayMessage("No card inserted.");
    }

    @Override
    public void enterPIN(String pin) {
        atm.getScreen().displayMessage("Please insert card first.");
    }

    @Override
    public void requestOperation() {
        atm.getScreen().displayMessage("Please insert card first.");
    }
}
