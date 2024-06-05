package lld.crypto;

import java.util.*;

class Wallet {
    private String walletId;
    private User user;
    private Map<String, Double> balances;
    private List<BalanceObserver> observers;

    public Wallet(String walletId, User user) {
        this.walletId = walletId;
        this.user = user;
        this.balances = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public void subscribeObserver(BalanceObserver observer) {
        observers.add(observer);
    }

    public void unsubscribeObserver(BalanceObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (BalanceObserver observer : observers) {
            observer.update(walletId);
        }
    }

    public void deposit(String currency, double amount) {
        balances.put(currency, balances.getOrDefault(currency, 0.0) + amount);
        notifyObservers();
    }

    public void withdraw(String currency, double amount) {
        double currentBalance = balances.getOrDefault(currency, 0.0);
        if (currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balances.put(currency, currentBalance - amount);
        notifyObservers();
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public String getWalletId() {
        return walletId;
    }

    public User getUser() {
        return user;
    }
}
