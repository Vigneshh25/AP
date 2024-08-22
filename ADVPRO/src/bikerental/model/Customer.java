package bikerental.model;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Customer Class
public class Customer {
    private String id;
    private String name;
    private double balance;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    public void addCharge(double amount) {
        this.balance += amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomerId() {
        return  id;
    }
}
