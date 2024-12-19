package ATM.version2.states;

import ATM.version2.hardware.ATM;

public class TransferState implements ATMState {
    private final ATM atm;

    public TransferState(ATM atm) {
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
        System.out.println("Enter target account number:");
        String targetAccount = atm.getScanner().next();  // Assuming keypad is used for entering the account number too
        System.out.println("Enter amount to transfer:");
        double transferAmount = atm.getScanner().nextDouble();
        atm.getAccountService().transfer(atm.getCurrentCardNumber(), targetAccount, transferAmount);
        System.out.println("Transfer successful.");
        atm.setState(atm.getAuthenticatedState());
    }
}
