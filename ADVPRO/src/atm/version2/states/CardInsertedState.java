package atm.version2.states;

import atm.version2.hardware.ATM;

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
    public void ejectCard() {
        atm.setCurrentCardNumber(null);
        atm.setState(atm.getIdleState());
        atm.getScreen().displayMessage("Card ejected.");
    }

    @Override
    public void enterPIN(String pin) {
        String cardNumber = atm.getCurrentCardNumber();
        if (atm.getAccountService().authenticate(cardNumber, pin)) {
            atm.getScreen().displayMessage("PIN entered. Please select an operation.");
            atm.setState(atm.getAuthenticatedState());
        } else {
            atm.getScreen().displayMessage("Incorrect PIN. Please try again.");
        }
    }

    @Override
    public void requestOperation() {
        atm.getScreen().displayMessage("Enter PIN first.");
    }
}
