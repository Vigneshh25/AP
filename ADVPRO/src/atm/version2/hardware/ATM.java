package atm.version2.hardware;

import atm.version2.dispenser.CashDispenser;
import atm.version1.devices.CardReader;
import atm.version1.devices.DepositSlot;
import atm.version1.devices.Keypad;
import atm.version1.devices.Screen;
import atm.version2.states.*;
import atm.version2.utils.AccountService;

public class ATM {
    private CardReader cardReader;
    private Keypad keypad;
    private Screen screen;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private Printer printer;
    private NetworkInfrastructure networkInfrastructure;
    private AccountService accountService;

    private ATMState currentState;
    private String currentCardNumber;

    private ATMState idleState;
    private ATMState cardInsertedState;
    private ATMState authenticatedState;
    private ATMState checkBalanceState;
    private ATMState withdrawState;
    private ATMState depositState;
    private ATMState transferState;

    public ATM(CardReader cardReader, Keypad keypad, Screen screen, CashDispenser cashDispenser,
               DepositSlot depositSlot, Printer printer, NetworkInfrastructure networkInfrastructure,
               AccountService accountService) {
        this.cardReader = cardReader;
        this.keypad = keypad;
        this.screen = screen;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.printer = printer;
        this.networkInfrastructure = networkInfrastructure;
        this.accountService = accountService;

        idleState = new IdleState(this);
        cardInsertedState = new CardInsertedState(this);
        authenticatedState = new AuthenticatedState(this);
        checkBalanceState = new CheckBalanceState(this);
        withdrawState = new WithdrawState(this);
        depositState = new DepositState(this);
        transferState = new TransferState(this);
        setState(idleState);
    }

    public void run() {
        while (true) {

            if (currentState == idleState) {
                currentState.insertCard();
            } else if (currentState == cardInsertedState) {
                String pin = keypad.enterPIN();
                currentState.enterPIN(pin);
            } else if (currentState == authenticatedState) {
                currentState.requestOperation();
            }
        }
    }

    public void setState(ATMState state) {
        this.currentState = state;
    }
    public ATMState getState() {
        return currentState;
    }

    public ATMState getIdleState() {
        return idleState;
    }

    public ATMState getCardInsertedState() {
        return cardInsertedState;
    }

    public ATMState getAuthenticatedState() {
        return authenticatedState;
    }

    public ATMState getCheckBalanceState() {
        return checkBalanceState;
    }

    public ATMState getWithdrawState() {
        return withdrawState;
    }

    public ATMState getDepositState() {
        return depositState;
    }

    public ATMState getTransferState() {
        return transferState;
    }

    public void setIdleState(ATMState idleState) {
        this.idleState = idleState;
    }

    public void setCardInsertedState(ATMState cardInsertedState) {
        this.cardInsertedState = cardInsertedState;
    }

    public void setAuthenticatedState(ATMState authenticatedState) {
        this.authenticatedState = authenticatedState;
    }

    public void setCheckBalanceState(ATMState checkBalanceState) {
        this.checkBalanceState = checkBalanceState;
    }

    public void setWithdrawState(ATMState withdrawState) {
        this.withdrawState = withdrawState;
    }

    public void setDepositState(ATMState depositState) {
        this.depositState = depositState;
    }

    public void setTransferState(ATMState transferState) {
        this.transferState = transferState;
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public Keypad getKeypad() {
        return keypad;
    }

    public Screen getScreen() {
        return screen;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public DepositSlot getDepositSlot() {
        return depositSlot;
    }

    public Printer getPrinter() {
        return printer;
    }

    public NetworkInfrastructure getNetworkInfrastructure() {
        return networkInfrastructure;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public String getCurrentCardNumber() {
        return currentCardNumber;
    }

    public void setCurrentCardNumber(String currentCardNumber) {
        this.currentCardNumber = currentCardNumber;
    }
}

