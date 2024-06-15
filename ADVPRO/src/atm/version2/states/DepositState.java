package atm.version2.states;

import atm.version2.hardware.ATM;

public class DepositState implements ATMState {
    private ATM atm;

    public DepositState(ATM atm) {
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
        atm.getScreen().displayMessage("Enter amount to deposit:");
        double depositAmount = atm.getKeypad().enterAmount();
        atm.getAccountService().deposit(atm.getCurrentCardNumber(), depositAmount);
        atm.getDepositSlot().acceptDeposit(depositAmount);
        atm.getScreen().displayMessage("Deposit successful.");
        atm.setState(atm.getAuthenticatedState());
    }
}
