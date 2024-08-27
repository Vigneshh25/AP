package atm.version2.hardware;

import atm.version2.dispenser.CashDispenser;
import atm.version2.states.*;
import atm.version2.utils.AccountService;

import java.util.Scanner;

public class ATM {
    private final CashDispenser cashDispenser;
    private final AccountService accountService;
    private final ATMState idleState;
    private final ATMState cardInsertedState;
    private final ATMState authenticatedState;
    private final ATMState checkBalanceState;
    private final ATMState withdrawState;
    private final ATMState depositState;
    private final ATMState transferState;
    private final Scanner scanner = new Scanner(System.in);
    private ATMState currentState;
    private String currentCardNumber;

    public ATM(CashDispenser cashDispenser, AccountService accountService) {
        this.cashDispenser = cashDispenser;
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

    public void run1() {
        while (true) {

            if (currentState == idleState) {
                currentState.insertCard();
            } else if (currentState == cardInsertedState) {
                System.out.print("Enter PIN: ");
                String pin = scanner.next();
                currentState.enterPIN(pin);
            } else if (currentState == authenticatedState) {
                currentState.requestOperation();
            }
        }
    }

    public void run() {
        while (true) {
            System.out.print("Enter Account Number :: ");
            String cardNumber = scanner.next();
            System.out.print("Enter Pin :: ");
            String pin = scanner.next();

            // For simplicity, assume authentication is successful
            boolean exit = false;
            while (!exit) {
                System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        double balance = accountService.checkBalance(cardNumber);
                        System.out.println("Balance: " + balance);
                        break;
                    case 2:
                        System.out.println("Enter amount to deposit:");
                        double depositAmount = scanner.nextDouble();
                        accountService.deposit(cardNumber, depositAmount);
                        System.out.println("Amount " + depositAmount + " Deposit successful.");
                        break;
                    case 3:
                        System.out.println("Enter amount to withdraw:");
                        double withdrawAmount = scanner.nextDouble();
                        accountService.withdraw(cardNumber, withdrawAmount);
                        cashDispenser.dispense((int) withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        System.out.println("Enter target account number:");
                        String targetAccount = scanner.next();  // Assuming scanner is used for entering the account number too
                        System.out.println("Enter amount to transfer:");
                        double transferAmount = scanner.nextDouble();
                        accountService.transfer(cardNumber, targetAccount, transferAmount);
                        System.out.println("Transfer successful.");
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    public ATMState getState() {
        return currentState;
    }

    public void setState(ATMState state) {
        this.currentState = state;
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

    public CashDispenser getCashDispenser() {
        return cashDispenser;
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

    public Scanner getScanner() {
        return scanner;
    }
}

