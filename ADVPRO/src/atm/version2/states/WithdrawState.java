package atm.version2.states;

import atm.version2.hardware.ATM;

public class WithdrawState implements ATMState {
    private ATM atm;

    public WithdrawState(ATM atm) {
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
        atm.getScreen().displayMessage("Enter amount to withdraw:");
        double withdrawAmount = atm.getKeypad().enterAmount();
        atm.getAccountService().withdraw(atm.getCurrentCardNumber(), withdrawAmount);
        atm.getCashDispenser().dispense((int) withdrawAmount);
        atm.getScreen().displayMessage("Withdrawal successful.");
        atm.setState(atm.getAuthenticatedState());
    }
}
