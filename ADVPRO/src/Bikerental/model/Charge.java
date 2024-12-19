package Bikerental.model;

import java.util.*;

// Charge Class
public class Charge {
    private String chargeId;
    private String customerId;
    private double amount;
    private Date date;

    public Charge(String chargeId, String customerId, double amount, Date date) {
        this.chargeId = chargeId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public String getChargeId() {
        return chargeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

