package ATM.version1.devices;

import ATM.version2.dispenser.CashDispenser;
import ATM.version1.repository.AccountService;

public class ATM {
    private CardReader cardReader;
    private Keypad keypad;
    private Screen screen;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private AccountService accountService;

    public ATM(CardReader cardReader, Keypad keypad, Screen screen, CashDispenser cashDispenser,
               DepositSlot depositSlot, AccountService accountService) {
        this.cardReader = cardReader;
        this.keypad = keypad;
        this.screen = screen;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.accountService = accountService;
    }

    public void run() {
        while (true) {
            String cardNumber = cardReader.readCard();
            String pin = keypad.enterPIN();

            // For simplicity, assume authentication is successful
            boolean exit = false;
            while (!exit) {
                screen.displayMessage("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
                int choice = Integer.parseInt(keypad.enterPIN());
                switch (choice) {
                    case 1:
                        double balance = accountService.checkBalance(cardNumber);
                        screen.displayMessage("Balance: " + balance);
                        break;
                    case 2:
                        screen.displayMessage("Enter amount to deposit:");
                        double depositAmount = keypad.enterAmount();
                        accountService.deposit(cardNumber, depositAmount);
                        depositSlot.acceptDeposit(depositAmount);
                        screen.displayMessage("Deposit successful.");
                        break;
                    case 3:
                        screen.displayMessage("Enter amount to withdraw:");
                        double withdrawAmount = keypad.enterAmount();
                        accountService.withdraw(cardNumber, withdrawAmount);
                        cashDispenser.dispense((int) withdrawAmount);
                        screen.displayMessage("Withdrawal successful.");
                        break;
                    case 4:
                        screen.displayMessage("Enter target account number:");
                        String targetAccount = keypad.enterPIN();  // Assuming keypad is used for entering the account number too
                        screen.displayMessage("Enter amount to transfer:");
                        double transferAmount = keypad.enterAmount();
                        accountService.transfer(cardNumber, targetAccount, transferAmount);
                        screen.displayMessage("Transfer successful.");
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        screen.displayMessage("Invalid choice. Please try again.");
                }
            }
        }
    }
}
