package atm.StateLayer;

import atm.ATM;
import atm.ModelLayer.TransactionType;

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
    public void removeCard() {
        atm.getScreen().displayMessage("Card removed.");
        atm.setCurrentCardNumber(null);
        atm.setCurrentState(new IdleState(atm));
    }

    @Override
    public void enterPIN(String pin) {
        atm.getScreen().displayMessage("PIN already entered.");
    }

    @Override
    public void selectOperation(TransactionType transactionType) {
        switch (transactionType) {
            case CHECK_BALANCE:
                atm.setCurrentState(new CheckBalanceState(atm));
                break;
            case WITHDRAW:
                atm.setCurrentState(new WithdrawState(atm));
                break;
            default:
                atm.getScreen().displayMessage("Invalid operation.");
        }
    }

    @Override
    public void checkBalance() {
        atm.getScreen().displayMessage("Select operation first.");
    }

    @Override
    public void withdrawCash(double amount) {
        atm.getScreen().displayMessage("Select operation first.");
    }
}
