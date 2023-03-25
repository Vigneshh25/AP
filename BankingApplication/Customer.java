package com.example.BankingApplication;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private String address;
    private String phone;
    private final List<Account> accounts;

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void transfer(Account sender, Account recipient, double amount) throws InsufficientFundsException {
        if (accounts.contains(sender) && accounts.contains(recipient)) {
            sender.transfer(recipient, amount);
            System.out.println("Account type: " + sender.getAccountType());
            System.out.println("Account balance: " + sender.getBalance());
            System.out.println("Transaction history:");
            List<Transaction> transactions = sender.getTransactions();
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getType() + " " + transaction.getAmount());
            }
            System.out.println();
        } else {
            throw new IllegalArgumentException("One or both accounts do not belong to this customer");
        }
    }


    // Verify account access with PIN
    public boolean verifyAccountAccess(Account account, String pin) {
        if (account instanceof SecureAccount secureAccount) {
            return secureAccount.verifyAccess(pin);
        } else {
            System.out.println("Account verification not supported for this account type.");
            return false;
        }
    }

    // Transfer money between accounts
    public void transferMoney(Account sender, Account recipient, double amount) throws InsufficientFundsException {
        if (sender.getBalance() >= amount) {
            sender.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}









