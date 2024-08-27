package atm.version2.states;

import atm.version2.hardware.ATM;

public class IdleState implements ATMState {
    private ATM atm;

    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        String cardNumber = atm.getScanner().next();
        atm.setCurrentCardNumber(cardNumber);
        atm.setState(atm.getCardInsertedState());
        System.out.println("Card inserted. Please enter your PIN.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card inserted.");
    }

    @Override
    public void enterPIN(String pin) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void requestOperation() {
        System.out.println("Please insert card first.");
    }
}
