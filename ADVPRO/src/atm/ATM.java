package atm;

import atm.InterfaceLayer.*;
import atm.ModelLayer.*;
import atm.ServiceLayer.AccountService;
import atm.StateLayer.ATMState;
import atm.StateLayer.IdleState;

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
        this.currentState = new IdleState(this);
    }

    public void run() {
        while (true) {
            currentState.insertCard();
        }
    }

    public void setCurrentState(ATMState state) {
        this.currentState = state;
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
