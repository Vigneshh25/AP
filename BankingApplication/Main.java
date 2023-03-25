package com.example.BankingApplication;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer("John Doe", "123 Main St", "555-1234");

        CheckingAccount checkingAccount = new CheckingAccount("1001", 1000, 500, "1234");
        customer.addAccount(checkingAccount);

        SavingsAccount savingsAccount = new SavingsAccount("2001", 5000.0, 2.5, "5678");
        customer.addAccount(savingsAccount);

        SecureAccount secureAccount = new SecureAccount("3001", 500, "9012");
        customer.addAccount(secureAccount);

        while (true) {
            System.out.println("Welcome to the bank. Choose an option:");
            System.out.println("1. Show customer information");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Add interest to savings account");
            System.out.println("5. Transfer money");
            System.out.println("6. Verify account access with PIN");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Customer name: " + customer.getName());
                    System.out.println("Customer address: " + customer.getAddress());
                    System.out.println("Customer phone: " + customer.getPhone());
                    System.out.println("Accounts:");
                    for (Account account : customer.getAccounts()) {
                        System.out.println(account.getAccountType() + " account " + account.getAccountNumber() + ": " + account.getBalance());
                    }
                    break;
                case 2:
                    System.out.println("Enter account number:");
                    String accountNumber = scanner.nextLine();
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Account accountToDeposit = null;
                    for (Account account : customer.getAccounts()) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            accountToDeposit = account;
                            break;
                        }
                    }
                    if (accountToDeposit == null) {
                        System.out.println("Account not found.");
                    } else {
                        accountToDeposit.deposit(depositAmount);
                        System.out.println("Deposit successful. New balance: " + accountToDeposit.getBalance());
                    }
                    break;
                case 3:
                    System.out.println("Enter account number:");
                    String accountNumber2 = scanner.nextLine();
                    System.out.println("Enter amount to withdraw:");
                    double withdrawalAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Account accountToWithdraw = null;
                    for (Account account : customer.getAccounts()) {
                        if (account.getAccountNumber().equals(accountNumber2)) {
                            accountToWithdraw = account;
                            break;
                        }
                    }
                    if (accountToWithdraw == null) {
                        System.out.println("Account not found.");
                    } else {
                        try {
                            accountToWithdraw.withdraw(withdrawalAmount);
                            System.out.println("Withdrawal successful. New balance: " + accountToWithdraw.getBalance());
                        } catch (InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    for (Account account : customer.getAccounts()) {
                        if (account instanceof SavingsAccount) {
                            SavingsAccount savingsAccount1 = (SavingsAccount) account;
                            savingsAccount1.addInterest(2.5);
                            System.out.println("Interest added to account " + savingsAccount1.getAccountNumber() + ".New balance: " + savingsAccount1.getBalance());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter account number to transfer from:");
                    String fromAccountNumber = scanner.nextLine();
                    System.out.println("Enter account number to transfer to:");
                    String toAccountNumber = scanner.nextLine();
                    System.out.println("Enter amount to transfer:");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Account fromAccount = null;
                    Account toAccount = null;
                    for (Account account : customer.getAccounts()) {
                        if (account.getAccountNumber().equals(fromAccountNumber)) {
                            fromAccount = account;
                        } else if (account.getAccountNumber().equals(toAccountNumber)) {
                            toAccount = account;
                        }
                    }
                    if (fromAccount == null) {
                        System.out.println("Transfer failed. Account " + fromAccountNumber + " not found.");
                    } else if (toAccount == null) {
                        System.out.println("Transfer failed. Account " + toAccountNumber + " not found.");
                    } else {
                        try {
                            fromAccount.transfer(toAccount, transferAmount);
                            System.out.println("Transfer successful. New balance of " + fromAccount.getAccountType() + " account " + fromAccount.getAccountNumber() + ": " + fromAccount.getBalance());
                            System.out.println("New balance of " + toAccount.getAccountType() + " account " + toAccount.getAccountNumber() + ": " + toAccount.getBalance());
                        } catch (InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Enter account number:");
                    String accountNumber3 = scanner.nextLine();
                    System.out.println("Enter PIN:");
                    String pin = scanner.nextLine();
                    Account accountToVerify = null;
                    for (Account account : customer.getAccounts()) {
                        if (account.getAccountNumber().equals(accountNumber3)) {
                            accountToVerify = account;
                            break;
                        }
                    }
                    if (accountToVerify == null) {
                        System.out.println("Account not found.");
                    } else {
                        boolean accessGranted = accountToVerify.verifyPIN(pin);
                        if (accessGranted) {
                            System.out.println("Access granted.");
                        } else {
                            System.out.println("Access denied.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Thank you for banking with us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }
}

