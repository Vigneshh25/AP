package BankingApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    private final List<Transaction> transactions;
    private String pin;

    public Account(String accountNumber, double balance, String accountType, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }
    public boolean verifyPIN(String pin) {
        return this.pin.equals(pin);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount));
        } else {
            throw new InsufficientFundsException("Insufficient balance!");
        }
    }

    public void addInterest(double rate) {
        if (Objects.equals(accountType, "savings")) {
            double interest = balance * rate / 100;
            balance += interest;
            transactions.add(new Transaction(TransactionType.INTEREST, interest));
        }
    }

    public void transfer(Account recipient, double amount) throws InsufficientFundsException {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add(new Transaction(TransactionType.TRANSFER, amount));
        } else {
            throw new InsufficientFundsException("Insufficient balance!");
        }
    }
}



