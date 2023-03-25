package com.example.BankingApplication;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit, String pin) {
        super(accountNumber, balance, "Checking", pin);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() + overdraftLimit >= amount) {
            super.withdraw(amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
