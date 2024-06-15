package atm.version2.states;

import atm.version2.hardware.ATM;

public class TransferState implements ATMState {
    private ATM atm;

    public TransferState(ATM atm) {
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
        atm.getScreen().displayMessage("PIN already entered.");
    }

    @Override
    public void requestOperation() {
        atm.getScreen().displayMessage("Enter target account number:");
        String targetAccount = atm.getKeypad().enterPIN();  // Assuming keypad is used for entering the account number too
        atm.getScreen().displayMessage("Enter amount to transfer:");
        double transferAmount = atm.getKeypad().enterAmount();
        atm.getAccountService().transfer(atm.getCurrentCardNumber(), targetAccount, transferAmount);
        atm.getScreen().displayMessage("Transfer successful.");
        atm.setState(atm.getAuthenticatedState());
    }
}
