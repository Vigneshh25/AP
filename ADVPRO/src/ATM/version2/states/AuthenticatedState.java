package ATM.version2.states;

import ATM.version2.hardware.ATM;

public class AuthenticatedState implements ATMState {
    private final ATM atm;

    public AuthenticatedState(ATM atm) {
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
        System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
        int choice = atm.getScanner().nextInt();  // Simplified choice entry

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
                System.err.println("Invalid choice. Please try again.");
                requestOperation();
        }
    }
}
