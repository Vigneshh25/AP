package bikerental.model;

public class Customer {
    public String id;
    String name;
    public double balance;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    public void addCharge(double amount) {
        this.balance += amount;
    }
}
