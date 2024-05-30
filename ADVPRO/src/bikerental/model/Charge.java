package bikerental.model;

import java.util.Date;

public class Charge {
    String chargeId;
    String customerId;
    double amount;
    Date date;

    public Charge(String chargeId, String customerId, double amount, Date date) {
        this.chargeId = chargeId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }
}
