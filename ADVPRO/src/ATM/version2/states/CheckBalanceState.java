package ATM.version2.states;

import ATM.version2.hardware.ATM;

public class CheckBalanceState implements ATMState {
    private final ATM atm;

    public CheckBalanceState(ATM atm) {
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
        System.out.println("Balance: " + atm.getAccountService().checkBalance(atm.getCurrentCardNumber()));
        atm.setState(atm.getAuthenticatedState());
    }
}
