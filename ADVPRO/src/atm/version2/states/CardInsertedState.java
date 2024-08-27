package atm.version2.states;

import atm.version2.hardware.ATM;

public class CardInsertedState implements ATMState {
    private final ATM atm;

    public CardInsertedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted.");
    }

    @Override
    public void ejectCard() {
        atm.setCurrentCardNumber(null);
        atm.setState(atm.getIdleState());
        System.out.println("Card ejected.");
    }

    @Override
    public void enterPIN(String pin) {
        String cardNumber = atm.getCurrentCardNumber();
        if (atm.getAccountService().authenticate(cardNumber, pin)) {
            System.out.println("PIN entered. Please select an operation.");
            atm.setState(atm.getAuthenticatedState());
        } else {
            System.out.println("Incorrect PIN. Please try again.");
        }
    }

    @Override
    public void requestOperation() {
        System.out.println("Enter PIN first.");
    }
}
