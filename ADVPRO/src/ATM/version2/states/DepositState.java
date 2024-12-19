package ATM.version2.states;

import ATM.version2.hardware.ATM;

public class DepositState implements ATMState {
    private final ATM atm;

    public DepositState(ATM atm) {
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
        System.out.println("PIN already entered.");
    }

    @Override
    public void requestOperation() {
        System.out.println("Enter amount to deposit:");
        double depositAmount = atm.getScanner().nextDouble();
        atm.getAccountService().deposit(atm.getCurrentCardNumber(), depositAmount);
        System.out.println("Accepted deposit of " + depositAmount + " rupees.");
        atm.setState(atm.getAuthenticatedState());
    }
}
