package atm.version2.states;

import atm.version2.hardware.ATM;

public class AuthenticatedState implements ATMState {
    private ATM atm;

    public AuthenticatedState(ATM atm) {
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
        atm.getScreen().displayMessage("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
        int choice = Integer.parseInt(atm.getKeypad().enterPIN());  // Simplified choice entry

        switch (choice) {
            case 1:
                atm.setState(atm.getCheckBalanceState());
                atm.getState().requestOperation();
                break;
            case 2:
                atm.setState(atm.getDepositState());
                atm.getState().requestOperation();
                break;
            case 3:
                atm.setState(atm.getWithdrawState());
                atm.getState().requestOperation();
                break;
            case 4:
                atm.setState(atm.getTransferState());
                atm.getState().requestOperation();
                break;
            case 5:
                atm.setState(atm.getIdleState());
                atm.getState().ejectCard();
                break;
            default:
                atm.getScreen().displayMessage("Invalid choice. Please try again.");
                requestOperation();
        }
    }
}
